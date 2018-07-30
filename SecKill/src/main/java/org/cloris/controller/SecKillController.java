package org.cloris.controller;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.service.SecKillService;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.GoodsVO;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecKillController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	SecKillService secKillService;

	/**
	 * JMeter测试情况：
	 * 
	 * 配置：Threads-5000 Loop-10
	 * 
	 * 聚合报告：Throughput-1850
	 */
	@PostMapping("/secKill")
	@ResponseBody
	public Result<OrderInfo> secKill(Model model, User user, @RequestParam("goodsId") Long id) {
		if (user == null) {
			return Result.error(CodeMessage.SESSION_ERROR);
		}
		// 判断秒杀商品库存
		GoodsVO goods = goodsService.findById(id);
		Integer stock = goods.getSecStock();
		if (stock <= 0) {
			return Result.error(CodeMessage.ACTIVITY_END);
		}
		// 判断是否已经秒杀
		SecOrder secOrder = orderService.findByUserIdAndGoodsId(user.getId(), id);
		if (secOrder != null) {
			return Result.error(CodeMessage.REPEAT_OPERATION);
		}
		// 减库存 下订单 写入秒杀订单
		OrderInfo order = secKillService.doSecKill(user, goods);
		return Result.success(order);
	}

}
