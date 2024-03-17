package com.hotel.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotel.service.exception.*;
import com.hotel.service.payload.ApiRespose;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiRespose> handlerResourseNotFoundException(
			ResourceNotFoundException ex){
	String message=	ex.getMessage();
	ApiRespose response=ApiRespose.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();
	
	return new ResponseEntity<ApiRespose>(response,HttpStatus.NOT_FOUND);
	}
}
