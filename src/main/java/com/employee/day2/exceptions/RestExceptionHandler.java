package com.employee.day2.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value=RecordNotFounException.class)
	public ResponseEntity<ApiErrors> handleRecorNotFounException(){
		ApiErrors error = new ApiErrors(400,"No record found.Please Enter Correct Id",new Date());
		return new ResponseEntity<ApiErrors>(error,HttpStatus.BAD_REQUEST);
	}
    
	
	@ExceptionHandler(value=RecordAlreadyPresentException.class)
	public ResponseEntity<ApiErrors> handleDuplicateRecordException(){
		ApiErrors error = new ApiErrors(400,"Record Already Present",new Date());
		return new ResponseEntity<ApiErrors>(error,HttpStatus.BAD_REQUEST);
	}
	
}
