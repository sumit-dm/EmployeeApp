package com.employee.day2.repository;

import java.io.Serializable;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.day2.entity.EmployeeEntity;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Serializable>{
	List<EmployeeEntity> findByEmail(String email);
	//List<EmployeeEntity> findByEmpId(Long empid);
	
}
