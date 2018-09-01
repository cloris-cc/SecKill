package org.cloris.service;

import org.cloris.domain.OrderInfo;
import org.cloris.domain.User;
import org.cloris.vo.GoodsVO;

public interface SecKillService {
	
	/**
	 * 减库存 下订单 写入秒杀订单
	 */
	OrderInfo doSecKill(User user, GoodsVO goods);
	
	/**
	 * 秒杀结果
	 */
	Long getResults(Long userId, Long goodsId);
	
	void setGoodsOver(Long goodsId);
	
	boolean getGoodsOver(Long goodsId);
}
