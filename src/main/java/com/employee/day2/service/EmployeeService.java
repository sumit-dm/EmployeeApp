package com.employee.day2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.employee.day2.entity.EmployeeEntity;
import com.employee.day2.exceptions.RecordNotFounException;
import com.employee.day2.model.Employee;
import com.employee.day2.repository.EmployeeRepository;
import com.employee.day2.validations.SalaryValidationObject;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public void saveEmployeeDetails(Employee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
	    BeanUtils.copyProperties(employee, employeeEntity);

	    // Set the SalaryValidationObject instance in the EmployeeEntity
	    employeeEntity.setSalary(employee.getSalary());

	    EmployeeEntity saveEmployeeEntity = employeeRepository.save(employeeEntity);
	}

	public List<Employee> getAllEmpl() {
		try {
			List<EmployeeEntity> allEntityList = employeeRepository.findAll();
			List<Employee> list = new ArrayList<>();

			if (!allEntityList.isEmpty()) {
				allEntityList.forEach(oftheentitypresentinAllentitieslist -> {
					Employee e = new Employee();
					BeanUtils.copyProperties(oftheentitypresentinAllentitieslist, e);
					list.add(e);
				});
			}
			return list;
		} catch (DataAccessException ex) {
			handleDataAccessException(ex);
			throw ex;
		}
	}

	/*
	 * public Employee findEmployeeById(Long empid) { Optional<EmployeeEntity>
	 * findById = employeeRepository.findById(empid); EmployeeEntity employeeEntity
	 * = findById.get(); Employee emp = new Employee();
	 * BeanUtils.copyProperties(employeeEntity, emp); return emp; }
	 */
	public Object findEmployeeById(Long empid) {
		Optional<EmployeeEntity> optionalEmpEntity = employeeRepository.findById(empid);
		if (optionalEmpEntity.isPresent()) {
			EmployeeEntity employeeEntity = optionalEmpEntity.get();
			Employee employee = new Employee();
			BeanUtils.copyProperties(employeeEntity, employee);
			return employee;
		} else {
			throw new RecordNotFounException("No record Found");

		}
	}

	public Object findByEmail(Employee employee) {
		try {
			List<EmployeeEntity> empList = employeeRepository.findByEmail(employee.getEmail());
//			if(empList.isEmpty()) {
//				return new Employee();
//			}
//			else {
//				EmployeeEntity employeeEntity = empList.get(0);
//				Employee emp1 = new Employee();
//				BeanUtils.copyProperties(employeeEntity, emp1);
//				return emp1;
//			}
			return empList.stream()
					.findFirst()
					.map(employeeEntityAlreadyPresent -> {
				                        Employee emp = new Employee();
				                        BeanUtils.copyProperties(employeeEntityAlreadyPresent, emp);
				                        return emp;})
			        .orElseGet(() -> new Employee());

		} catch (DataAccessException ex) {
			handleDataAccessException(ex);
			return "An error occurred while fetching data.";
		}
	}

	private void handleDataAccessException(DataAccessException ex) {
		ex.printStackTrace();
		//
		//
		//
	}

	public Employee findEmployeeById2(Long empid) {
		Optional<EmployeeEntity> findById2 = employeeRepository.findById(empid);
		EmployeeEntity employeeEntity = findById2.get();
		Employee emp = new Employee();
		BeanUtils.copyProperties(employeeEntity, emp);
		return emp;
	}

	public Employee updateEmployeeById(Long empid, Employee employee) {
		try {
			Optional<EmployeeEntity> optionalEmpEntity = employeeRepository.findById(empid);
			EmployeeEntity existingEmpEntity = optionalEmpEntity
					.orElseThrow(() -> new RecordNotFounException("Employee not found with id: " + empid));

			// Create a new entity for the update
			EmployeeEntity updateEmpEntity = new EmployeeEntity();

			// Copy properties from existing entity
			BeanUtils.copyProperties(existingEmpEntity, updateEmpEntity);

			// Copy properties from the incoming Employee object
			BeanUtils.copyProperties(employee, updateEmpEntity);

			// Set the empid explicitly (in case it's not set in the Employee object)
			updateEmpEntity.setEmpid(empid);

			// Save the updated entity
			updateEmpEntity = employeeRepository.save(updateEmpEntity);

			// Convert the updated entity to Employee and return
			Employee updatedEmp = new Employee();
			BeanUtils.copyProperties(updateEmpEntity, updatedEmp);

			// Set the empid explicitly
			updatedEmp.setEmpid(updateEmpEntity.getEmpid());

			return updatedEmp;
		} catch (DataAccessException ex) {
			handleDataAccessException(ex);
			throw ex;
		}
	}

	public Employee deleteEmp(Long empid) {
		try {
			Optional<EmployeeEntity> findById2 = employeeRepository.findById(empid);
			EmployeeEntity employeeEntity = findById2.get();
			if (employeeEntity.getEmail() != null) {
				employeeRepository.deleteById(empid); //already present in repo.
				Employee emp = new Employee();
				BeanUtils.copyProperties(employeeEntity, emp);
				return emp;

			} else {

				throw new RecordNotFounException("No record Found");
			}
		} catch (DataAccessException ex) {
			handleDataAccessException(ex);
			throw ex;
		}
	}
	
	public List<EmployeeEntity> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy){
		
		Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
		Page<EmployeeEntity> pagedResult = employeeRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
			return pagedResult.getContent();
		}else {
			return new ArrayList<EmployeeEntity>();
		}
		
	}
}
