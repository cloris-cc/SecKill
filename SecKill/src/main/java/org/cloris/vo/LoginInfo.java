package org.cloris.vo;

import javax.validation.constraints.NotNull;

import org.cloris.utils.validator.IsMobile;

import lombok.Data;

@Data
public class LoginInfo {
	@NotNull
	@IsMobile
	private String mobile;
	@NotNull
	private String password;
}
