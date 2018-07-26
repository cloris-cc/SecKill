package org.cloris.service.impl;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.service.SecKillService;
import org.cloris.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecKillServiceImpl implements SecKillService {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Override
	@Transactional
	public OrderInfo doSecKill(User user, GoodsVO goods) {
		
		goodsService.reduceStock(goods);
		
		return orderService.createOrder(user,goods);
	}

}
