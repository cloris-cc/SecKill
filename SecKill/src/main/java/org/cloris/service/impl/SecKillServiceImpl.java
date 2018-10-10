package org.cloris.service.impl;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.service.GoodsService;
import org.cloris.service.OrderService;
import org.cloris.service.SecKillService;
import org.cloris.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecKillServiceImpl implements SecKillService {

	@Autowired
	GoodsService goodsService;

	@Autowired
	OrderService orderService;

	@Autowired
	RedisTemplate<String, Boolean> template;

	@Override
	@Transactional
	public OrderInfo doSecKill(User user, GoodsVO goods) {

		Boolean success = goodsService.reduceStock(goods);
		if (success) {
			return orderService.createOrder(user, goods);
		} else {
			setGoodsOver(goods.getId());
			return null;
		}
	}

	@Override
	public Long getResults(Long userId, Long goodsId) throws NullPointerException {
		SecOrder secOrder = orderService.findByUserIdAndGoodsId(userId, goodsId);
		if (secOrder != null) { // 秒杀成功
			System.out.println("秒杀成功");
			System.out.println("秒杀订单信息：" + secOrder);
			return secOrder.getOrderId();
		} else {
			boolean isOver = getGoodsOver(goodsId);
			if (isOver) {
				return -1L;
			} else {
				return 0L;
			}
		}
	}

	@Override
	public void setGoodsOver(Long goodsId) {
		template.opsForValue().set("" + goodsId, true);
	}

	@Override
	public boolean getGoodsOver(Long goodsId) throws NullPointerException{
		return template.opsForValue().get("" + goodsId);
	}

}
