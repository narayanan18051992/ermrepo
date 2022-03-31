package com.erms.batchprocessing;

import java.text.SimpleDateFormat;
import java.util.Date;



import org.springframework.batch.item.ItemProcessor;

import com.erms.model.Employee;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {


  @Override
  public Employee process(final Employee employee) throws Exception {
	  Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(employee.getDob());  
	  Date date2=new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1995");   
	  if(date2.after(date1)||date1.equals(date2)) {
		 employee.setAgeCategory("Senior Employee");
	  }else {
		  employee.setAgeCategory("");
	  }
	  if(employee.getRating()>=90) {
		  employee.setPerformanceLevel("High Performer");
	  }else {
		  employee.setPerformanceLevel("");
	  }

    return employee;
  }
}