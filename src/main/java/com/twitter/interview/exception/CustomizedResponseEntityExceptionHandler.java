package com.twitter.interview.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
		ApplicationExceptionResponse exceptionResp = new ApplicationExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity(exceptionResp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	
		ApplicationExceptionResponse exceptionResp = new ApplicationExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false));
		return new ResponseEntity(exceptionResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApplicationExceptionResponse exceptionResp = new ApplicationExceptionResponse(new Date(), ex.getMessage(), 
				ex.getBindingResult().getFieldError().getField() + " field value is not valid");
		return new ResponseEntity(exceptionResp, HttpStatus.BAD_REQUEST);
	}
}
