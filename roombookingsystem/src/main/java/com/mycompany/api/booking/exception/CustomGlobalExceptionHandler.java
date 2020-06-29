package com.mycompany.api.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// user not found exception
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotfoundException(UserNameNotFoundException e,
			WebRequest request) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
