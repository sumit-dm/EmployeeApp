package com.employee.day2.exceptions;

public class RecordNotFounException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RecordNotFounException(String msg) {
		super(msg);
	}
}
