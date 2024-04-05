package com.employee.day2.exceptions;

public class RecordAlreadyPresentException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
	public RecordAlreadyPresentException(String msg) {
		super(msg);
	}
}
