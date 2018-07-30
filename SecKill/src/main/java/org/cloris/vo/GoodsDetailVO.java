package org.cloris.vo;

import org.cloris.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoodsDetailVO {
	private int status = 0;
	private int remainSeconds = 0;
	private GoodsVO goods;
	private User user;
}
