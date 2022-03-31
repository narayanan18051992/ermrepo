package com.erms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erms.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>
{

}
