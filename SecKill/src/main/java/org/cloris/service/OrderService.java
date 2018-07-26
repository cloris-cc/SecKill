package org.cloris.service;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.SecOrder;
import org.cloris.domain.User;
import org.cloris.vo.GoodsVO;

public interface OrderService {
	
	SecOrder findByUserIdAndGoodsId(Long userId, Long goodsId);

	OrderInfo createOrder(User user, GoodsVO goods);
	
}
