package org.cloris.service.impl;

import java.util.Date;

import org.cloris.dao.OrderDao;
import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.service.OrderService;
import org.cloris.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	RedisTemplate<String, SecOrder> template;

	@Override
	public SecOrder findByUserIdAndGoodsId(Long userId, Long goodsId) {
		// 查询 Redis
		return template.opsForValue().get(userId + "_" + goodsId);
	}

	@Override
	@Transactional
	public OrderInfo createOrder(User user, GoodsVO goods) {

		OrderInfo order = new OrderInfo();
		order.setCreateDate(new Date());
		order.setShippingAddrId(0L);
		order.setGoodsCount(1);
		order.setGoodsId(goods.getId());
		order.setGoodsName(goods.getName());
		order.setGoodsPrice(goods.getSecPrice());
		order.setOrderChannel(1);
		order.setStatus(0);
		order.setUserId(user.getId());

		System.out.println(order);
		orderDao.insertOrder(order);

		SecOrder secOrder = new SecOrder();
		secOrder.setGoodsId(goods.getId());
		secOrder.setUserId(user.getId());
		secOrder.setOrderId(order.getId());

		orderDao.insertSecOrder(secOrder);

		template.opsForValue().set(user.getId() + "_" + goods.getId(), secOrder);

		return order;
	}

	@Override
	public OrderInfo findById(Long orderId) {
		return orderDao.findById(orderId);
	}

	

}
