package com.employee.day2.response;

import lombok.Data;

@Data
public class ResponseMessage<T> {
	private String message;
	private Integer status;
	private T data;

}
