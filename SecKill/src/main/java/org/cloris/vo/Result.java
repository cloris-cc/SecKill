package org.cloris.vo;

import lombok.Getter;

@Getter
public class Result<T> {

	public int code;
	public String message;
	public T data;

	private Result(T data) {
		this.code = 200;
		this.message = "success";
		this.data = data;
	}

	private Result(CodeMessage codeMessage) {
		this.code = codeMessage.getCode();
		this.message = codeMessage.getMessage();
	}

	/**
	 * 处理成功调用的方法
	 */
	public static <T> Result<T> success(T data) {
		return new Result<T>(data);
	}

	/**
	 * 处理失败调用的方法
	 */
	public static <T> Result<T> error(CodeMessage codeMessage) {
		return new Result<T>(codeMessage);
	}

}
