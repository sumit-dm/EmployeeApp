package com.employee.day2.exceptions;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ApiErrors {
	
	private Integer errorCode;
	private String errorDesc;
	private Date date;
	
	
}
