package com.blog.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> getResourceNotFound(ResourceNotFoundException ex){
		
		String msg = ex.getMessage();
		ApiResponse res = new ApiResponse(msg , false);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> getMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> res = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			res.put(fieldName, errorMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(res , HttpStatus.BAD_REQUEST);
	}

}
