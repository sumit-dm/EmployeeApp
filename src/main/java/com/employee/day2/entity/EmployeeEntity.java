package com.employee.day2.entity;

import com.employee.day2.validations.SalaryValidationObject;
import com.employee.day2.validations.ValidEmail;
import com.employee.day2.validations.ValidSalary;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "EmployeeDetails")
public class EmployeeEntity {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EmployeeId")
	private Long empid;

	
	@Column(name = "EmployeeName")
    private String name;

	@Column(name = "EmployeeSalary")
	@ValidSalary
	private SalaryValidationObject salary;

	@Column(name = "EmployeeGender")
	private String gender;
	
	@Column(name = "Email")
	@ValidEmail(message = "Invalid email address")
	private String email;
	
	@Column(name = "Address")
	private String address;
	
}
