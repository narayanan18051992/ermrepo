package com.erms.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erms.batchprocessing.EmployeeItemProcessor;
import com.erms.model.Employee;


public class ValidationProcessor {

	private static final Logger logger = LoggerFactory.getLogger(ValidationProcessor.class);
	

	public static Boolean process(final Employee emp) throws Exception {
		
		EmployeeItemProcessor processor=new EmployeeItemProcessor();
		Employee result=processor.process(emp);
		return true;
	}
	}
