package org.cloris.vo;

import java.util.Date;

import org.cloris.domain.Goods;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsVO extends Goods {
	private static final long serialVersionUID = -6928309488077368723L;
	private Double secPrice;
	private Integer secStock;
	private Date startDate;
	private Date endDate;

}
