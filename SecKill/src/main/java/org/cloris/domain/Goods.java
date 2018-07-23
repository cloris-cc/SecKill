package org.cloris.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Goods implements Serializable {

	private static final long serialVersionUID = 7487745092299970947L;
	private Long id;
	private String name;
	private String title;
	private String image;
	private String detail;
	private Double price;
	private Integer stock;

}
