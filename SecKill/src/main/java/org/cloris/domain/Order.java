package org.cloris.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Order implements Serializable {

	private static final long serialVersionUID = 7115067019784178576L;
	private Long id;
	private Long userId;
	private Long goodsId;
	private String shippingAddrId;
	private String goodsName;
	private Integer goodsCount;
	private Double goodsPrice;
	private Integer orderChannel;
	private Integer status;
	private Date createDate;
	private Date payDate;
}
