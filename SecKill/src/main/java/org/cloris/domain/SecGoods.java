package org.cloris.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class SecGoods implements Serializable {

	private static final long serialVersionUID = 8529681111285078969L;
	private Long id;
	private Long goodsId;
	private Double price;
	private Integer stock;
	private Date startDate;
	private Date endDate;
}
