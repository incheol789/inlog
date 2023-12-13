package com.inlog.controller;

import com.inlog.exception.InlogException;
import com.inlog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ExceptionController {

	@ResponseBody
	@ExceptionHandler(InlogException.class)
	public ResponseEntity<ErrorResponse> InlogException(InlogException e) {
		int statusCode = e.getStatusCode();

		ErrorResponse body = ErrorResponse.builder()
				.code(String.valueOf(statusCode))
				.message(e.getMessage())
				.validation(e.getValidation())
				.build();

		ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
				.body(body);

		return response;
	}
}
