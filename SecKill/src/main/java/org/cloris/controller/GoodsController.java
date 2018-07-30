package org.cloris.controller;

import java.util.List;

import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.vo.GoodsDetailVO;
import org.cloris.vo.GoodsVO;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoodsController {

	@Autowired
	GoodsService goodsService;

	/**
	 * JMeter测试情况：
	 * 配置：Threads-5000 Loop-10
	 * 聚合报告：Throughput-1850
	 */
	@GetMapping("/goods")
	public String goodsList(Model model, User user) {
		model.addAttribute("user", user);
		// 查询商品列表
		List<GoodsVO> goods = goodsService.showAll();
		model.addAttribute("goods", goods);
		return "goods";
	}

	@GetMapping("/goods/{id}")
	@ResponseBody
	public Result<GoodsDetailVO> goodsDetail(Model model, User user, @PathVariable("id") Long id) {
//		model.addAttribute("user", user);
		// 查询商品的详细信息
		GoodsVO goods = goodsService.findById(id);
//		model.addAttribute("goods", goods);
		long start = goods.getStartDate().getTime();
		long end = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();
		// 秒杀活动状态 0:未开始 1:进行中 2:已结束
		int status = 0;
		// 活动倒计时
		int remainSeconds = 0;

		if (now < start) { // 秒杀还没开始,进行倒计时
			status = 0;
			remainSeconds = (int) (start - now) / 1000;
		} else if (now > end) { // 秒杀已经结束
			status = 2;
			remainSeconds = -1;
		} else { // 秒杀进行中
			status = 1;
			remainSeconds = 0;
		}
//		model.addAttribute("status", status);
//		model.addAttribute("remainSeconds", remainSeconds);
		GoodsDetailVO vo = new GoodsDetailVO(status, remainSeconds, goods, user);
//		return "goods_detail";
		return Result.success(vo);
	}

//	@GetMapping("/goods")
//	@ResponseBody
//	public List<GoodsVO> goods() {
//		return goodsService.showAll();
//	}

}
