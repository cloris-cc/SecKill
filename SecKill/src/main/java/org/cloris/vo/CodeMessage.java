package org.cloris.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeMessage {
	private int code;
	private String message;
	
	public static CodeMessage SUCCESS = new CodeMessage(200, "success");
	public static CodeMessage SERVER_ERROR = new CodeMessage(500, "server error");
}
