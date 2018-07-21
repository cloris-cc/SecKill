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
		if (e instanceof BindException) {
			String defaultMessage = ((BindException) e).getAllErrors().get(0).getDefaultMessage();
			return Result.error(CodeMessage.BIND_ERROR.fillArgs(defaultMessage));
		} else {
			return Result.error(CodeMessage.SERVER_ERROR);
		}
	}
}
