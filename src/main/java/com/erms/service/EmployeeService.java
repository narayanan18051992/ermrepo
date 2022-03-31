package com.erms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erms.model.Employee;
import com.erms.repository.EmployeeRepository;




@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public void saveOrUpdate(Employee employee) 
	{
		employeeRepository.save(employee);
	}

}
