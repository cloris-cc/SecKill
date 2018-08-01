package org.cloris.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeMessage {
	private int code;
	private String message;

	// 通用错误码
	public static CodeMessage SUCCESS = new CodeMessage(200, "success");
	public static CodeMessage SERVER_ERROR = new CodeMessage(500100, "服务端异常");
	public static CodeMessage BIND_ERROR = new CodeMessage(500101, "参数校验错误: %s");

	// 登录模块
	public static CodeMessage SESSION_ERROR = new CodeMessage(400210, "Session不存在或已失效");
	public static CodeMessage PASSWORD_EMPTY = new CodeMessage(400211, "密码不能为空");
	public static CodeMessage MOBILE_EMPTY = new CodeMessage(400212, "手机号码不能为空");
	public static CodeMessage MOBILE_WRONG = new CodeMessage(400213, "手机号码格式错误");
	public static CodeMessage MOBILE_NOT_EXIST = new CodeMessage(400214, "用户不存在");
	public static CodeMessage PASSWORD_WRONG = new CodeMessage(400215, "密码错误");
	
	// 秒杀模块
	public static CodeMessage ACTIVITY_END = new CodeMessage(500500, "商品已经秒杀完毕");
	public static CodeMessage REPEAT_OPERATION = new CodeMessage(500501, "不能重复秒杀");
	
	// 订单模块
	public static CodeMessage ORDER_NOT_EXIST = new CodeMessage(500400, "订单不存在");
	
	public CodeMessage fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.message, args);
		return new CodeMessage(code, message);
	}

}
