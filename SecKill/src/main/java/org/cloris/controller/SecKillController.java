package org.cloris.controller;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.service.SecKillService;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecKillController {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	SecKillService secKillService;

	@PostMapping("/secKill")
	public String secKill(Model model, User user, @RequestParam("goodsId") Long id) {
		if (user == null) {
			return "login";
		}
		// 判断秒杀商品库存
		GoodsVO goods = goodsService.findById(id);
		Integer stock = goods.getSecStock();
		if (stock <= 0) {
			model.addAttribute("errmsg", CodeMessage.ACTIVITY_END.getMessage());
			return "secKill_fail";
		}
		// 判断是否已经秒杀
		SecOrder secOrder = orderService.findByUserIdAndGoodsId(user.getId(), id);
		if (secOrder != null) {
			model.addAttribute("errmsg", CodeMessage.REPEAT_OPERATION.getMessage());
			return "secKill_fail";
		}
		// 减库存 下订单 写入秒杀订单
		OrderInfo order = secKillService.doSecKill(user, goods);
		model.addAttribute("orderInfo", order);
		model.addAttribute("goods", goods);

		return "order_detail";
	}

}
