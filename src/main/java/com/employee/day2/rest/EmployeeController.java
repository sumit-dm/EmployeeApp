package com.employee.day2.rest;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.day2.entity.EmployeeEntity;
import com.employee.day2.exceptions.RecordAlreadyPresentException;
import com.employee.day2.exceptions.RecordNotFounException;
import com.employee.day2.model.Employee;
import com.employee.day2.response.ResponseMessage;
import com.employee.day2.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
    
	////1. Save Employee Details
	@PostMapping("/saveEmployee") // Save Employee
	public ResponseEntity<ResponseMessage<String>> addEmployeeDetails(@RequestBody Employee employee) {
		ResponseMessage<String> responseMessage = null;
		Employee emp = (Employee) employeeService.findByEmail(employee);

		if (emp.getEmail() == null) {
			employeeService.saveEmployeeDetails(employee);
			responseMessage = new ResponseMessage<>();
			responseMessage.setData(employee.toString());
			responseMessage.setMessage("Success");
			responseMessage.setStatus(200);
			return ResponseEntity.ok().body(responseMessage);
		} else {
			throw new RecordAlreadyPresentException("Duplicate Record");
		}
	}
	
	//2. Get All Employees
	@GetMapping("/getAllEmployees") 
	public ResponseEntity<ResponseMessage<String>> getAllEmployees() {
		List<Employee> allEmpl = employeeService.getAllEmpl();
		if (allEmpl.size() == 0) {
			throw new RecordNotFounException("No record Found");
		}
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(allEmpl.toString());
		responseMessage.setMessage("Success");
		responseMessage.setStatus(10);
		return ResponseEntity.ok().body(responseMessage);
	}

	@GetMapping("/getEmployeeById") // Get EmployeeById - Query Param
	public ResponseEntity<ResponseMessage<String>> getEmployeeById(@RequestParam("empid") Long empid) {
		Employee findEmployeeById = (Employee) employeeService.findEmployeeById(empid); // new change here -->(Employee)
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(findEmployeeById.toString());
		responseMessage.setMessage("Success");
		responseMessage.setStatus(201);
		return ResponseEntity.ok().body(responseMessage);
	}

	@GetMapping("/getEmployeeById2/{empid}") // Get EmployeeById - Path Param
	public ResponseEntity<ResponseMessage<String>> getEmployeeById2(@PathVariable("empid") Long empid) {
		Employee findEmployeeById2 = (Employee) employeeService.findEmployeeById(empid);

		if (findEmployeeById2.getEmail() != null) {
			ResponseMessage<String> responseMessage = new ResponseMessage<>();
			responseMessage.setData(findEmployeeById2.toString());
			responseMessage.setMessage("Success");
			responseMessage.setStatus(202);
			return ResponseEntity.ok().body(responseMessage);
		} else {
			throw new RecordNotFounException("No record Found");
		}
	}
    
	//
	@PutMapping("/update/{empid}")
	public ResponseEntity<ResponseMessage<String>> updateEmployee(@PathVariable("empid") Long empid,
			@RequestBody Employee employee) {
		Employee emp = (Employee) employeeService.findEmployeeById(empid);
		if (emp.getEmail() != null) {
			employeeService.updateEmployeeById(empid, employee);
			ResponseMessage<String> responseMessage = new ResponseMessage<>();
			responseMessage.setData(employee.toString());
			responseMessage.setMessage("Success");
			responseMessage.setStatus(203);
			return ResponseEntity.ok().body(responseMessage);
		} else {
			throw new RecordNotFounException("No record Found");
		}
	}

	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<ResponseMessage<String>> deleteEmployee(@PathVariable("empid") Long empid) {
		Employee emp = (Employee) employeeService.findEmployeeById(empid);
		if (emp.getEmail() != null) {
		Employee deletedEmployee = employeeService.deleteEmp(empid);
		ResponseMessage<String> responseMessage = new ResponseMessage<>();
		responseMessage.setData(deletedEmployee.toString());
		responseMessage.setMessage("Employee Deleted");
		responseMessage.setStatus(204);
		return ResponseEntity.ok().body(responseMessage);
		}else {
			throw new RecordNotFounException("No record Found");
		}
	}
    
	@GetMapping(value= "/getAllEmployeesByPagingSorting")
	public ResponseEntity<ResponseMessage<List<EmployeeEntity>>> getAllEmployeesPagingAndSorting(
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "empid") String sortBy) {  //JPA provider will handle the conversion of the String representation of the field name(Long empid) to the actual field in the database.

		List<EmployeeEntity> list = employeeService.getAllEmployees(pageNo, pageSize, sortBy);
		ResponseMessage<List<EmployeeEntity>> responseMessage = new ResponseMessage<>();
		responseMessage.setData(list);
		responseMessage.setMessage("success");
		responseMessage.setStatus(204);
		return ResponseEntity.ok().body(responseMessage);
	}
}	
