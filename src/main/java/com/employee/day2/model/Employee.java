package com.employee.day2.model;

import com.employee.day2.validations.SalaryValidationObject;

import lombok.Data;

@Data
public class Employee {

	private Long empid;
	private String name;
	private SalaryValidationObject salary;
	private String gender;
	private String email;
	private String address;
	

	/*
	 * public Long getId() { return id; }
	 * 
	 * public void setId(Long id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public Double getSalary() { return salary; }
	 * 
	 * public void setSalary(Double salary) { this.salary = salary; }
	 * 
	 * public String getGender() { return gender; }
	 * 
	 * public void setGender(String gender) { this.gender = gender; }
	 * 
	 * @Override public String toString() { return "Employee [id=" + id + ", name="
	 * + name + ", salary=" + salary + ", gender=" + gender + "]"; }
	 */
}
