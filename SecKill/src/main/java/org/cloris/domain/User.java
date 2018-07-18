package org.cloris.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = -300092622430419800L;
	private Integer id;
	private String name;
}
