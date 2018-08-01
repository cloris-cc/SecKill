package org.cloris.vo;

import org.cloris.domain.OrderInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDetailVO {
	private GoodsVO goods;
	private OrderInfo order;

}
