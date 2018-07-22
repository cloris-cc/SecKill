package org.cloris.utils.exception;

import javax.servlet.http.HttpServletRequest;

import org.cloris.vo.CodeMessage;
import org.cloris.vo.Result;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionController {

	@ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest request, Exception e) {
		if (e instanceof GlobalException) { // 处理GlobalException全局异常
			CodeMessage message = ((GlobalException) e).getCodeMessage();
			return Result.error(message);
		} else if (e instanceof BindException) { // 处理参数绑定异常
			String defaultMessage = ((BindException) e).getAllErrors().get(0).getDefaultMessage();
			return Result.error(CodeMessage.BIND_ERROR.fillArgs(defaultMessage));
		} else {
			e.printStackTrace();
			return Result.error(CodeMessage.SERVER_ERROR);
		}
	}
}
