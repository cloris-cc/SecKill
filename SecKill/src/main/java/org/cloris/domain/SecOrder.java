package org.cloris.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class SecOrder implements Serializable {

	private static final long serialVersionUID = 7534302343733970868L;
	private Long id;
	private Long userId;
	private Long orderId;
	private Long goodsId;

}
