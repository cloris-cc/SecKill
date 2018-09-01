package org.cloris.controller;

import java.util.HashMap;
import java.util.List;

import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.rabbitmq.SecKillMQSender;
import org.cloris.rabbitmq.SecKillMessage;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.service.SecKillService;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.GoodsVO;
import org.cloris.vo.Result;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecKillController implements InitializingBean {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	SecKillService secKillService;

	@Autowired
	SecKillMQSender sender;

	@Autowired
	RedisTemplate<Long, Integer> template;

	private HashMap<Long, Boolean> localOverMap = new HashMap<>();

	/*
	 * 系统初始化：把商品库存加载到 redis 中
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<GoodsVO> goodsList = goodsService.showAll();
		if (goodsList == null) {
			return;
		}
		for (GoodsVO goods : goodsList) {
			template.opsForValue().set(goods.getId(), goods.getSecStock());
			localOverMap.put(goods.getId(), false);
		}
	}

	/**
	 * JMeter测试情况：
	 * 配置：Threads-5000 Loop-10
	 * 聚合报告：Throughput-1850
	 */
	@PostMapping("/secKill")
	@ResponseBody
	public Result<Integer> secKill(Model model, User user, @RequestParam("goodsId") Long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			return Result.error(CodeMessage.SESSION_ERROR);
		}

		// 内存标记，减少 Redis 访问
		boolean over = localOverMap.get(goodsId);
		if (over) {
			return Result.error(CodeMessage.ACTIVITY_END);
		}

		// 预减库存
		Integer stock = template.opsForValue().get(goodsId);
		stock--;
		template.opsForValue().set(goodsId, stock);
		if (stock < 0) {
			localOverMap.put(goodsId, true);
			return Result.error(CodeMessage.ACTIVITY_END);
		}

		// 判断是否已经秒杀
		SecOrder secOrder = orderService.findByUserIdAndGoodsId(user.getId(), goodsId);
		if (secOrder != null) {
			return Result.error(CodeMessage.REPEAT_OPERATION);
		}

		// 入队
		SecKillMessage secKillMessage = new SecKillMessage(user, goodsId);
		sender.sendMessage(secKillMessage);
		return Result.success(0); // 0 : 排队中

	}

	/**
	 * @return : OrderId--成功 -1L--秒杀失败 0L--排队中
	 */
	@GetMapping("/results")
	@ResponseBody
	public Result<Long> secKillResults(Model model, User user, @RequestParam("goodsId") Long goodsId) {
		model.addAttribute("user", user);
		if (user == null) {
			return Result.error(CodeMessage.SESSION_ERROR);
		}

		Long results = secKillService.getResults(user.getId(), goodsId);
		return Result.success(results);
	}

}
