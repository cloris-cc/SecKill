package org.cloris.utils.exception;

import org.cloris.vo.CodeMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private CodeMessage codeMessage;
	

}
