package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice	// 모든 Exception이 발생했을 때 이 곳으로 오게 한다.
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)	// 모든 Exception을 처리하고 싶다면 Exception을 매개변수로 둔다.
	public ResponseDto<String> handleArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}
	
}
