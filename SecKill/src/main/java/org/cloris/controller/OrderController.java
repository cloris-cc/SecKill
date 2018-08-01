package org.cloris.controller;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.vo.CodeMessage;
import org.cloris.vo.GoodsVO;
import org.cloris.vo.OrderDetailVO;
import org.cloris.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	GoodsService goodsService;

	@GetMapping("/detail/{orderId}")
	public Result<OrderDetailVO> info(User user, @PathVariable("orderId") Long orderId) {
		if (user == null) {
			return Result.error(CodeMessage.SESSION_ERROR);
		}
		OrderInfo order = orderService.findById(orderId);
		if (order == null) {
			return Result.error(CodeMessage.ORDER_NOT_EXIST);
		}
		GoodsVO goods = goodsService.findById(order.getGoodsId());
		OrderDetailVO vo = new OrderDetailVO(goods, order);
		return Result.success(vo);
	}

}
