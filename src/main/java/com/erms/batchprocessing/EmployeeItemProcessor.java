package com.erms.batchprocessing;

import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import org.springframework.batch.item.ItemProcessor;

import com.erms.aspect.ERMAspect;
import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.util.ERMUtil;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeItemProcessor.class);

	@Override
	public Employee process(final Employee employee) throws Exception {     //processor validate input details
		long startTime=System.currentTimeMillis();
		logger.info("Processor Started: " + employee.getName() +" at time "+startTime);
		 Thread.sleep(1000);
		ERMUtil.getInstance();
		Date date1 = new SimpleDateFormat(ERMConstants.DT_FORMAT_DD_MM_YYYY).parse(employee.getDob());
		Date date2 = new SimpleDateFormat(ERMConstants.DT_FORMAT_DD_MM_YYYY).parse(ERMUtil.getProperties().getProperty(ERMConstants.EMP_DOB));
		if (date2.after(date1) || date1.equals(date2)) {
			employee.setAgeCategory(ERMUtil.getProperties().getProperty(ERMConstants.EMP_AGE_CATEGORY));
		} else {
			employee.setAgeCategory("");
		}
		if (employee.getRating() >= ERMConstants.PERFORMANCE_THRESHOLD) {
			employee.setPerformanceLevel(ERMUtil.getProperties().getProperty(ERMConstants.EMP_PERFORMANCE_LEVEL));
		} else {
			employee.setPerformanceLevel("");
		}
		long endTime=System.currentTimeMillis();
		logger.info("Processor Completed: " + endTime);
		logger.info("Total time taken to process items : "+(endTime-startTime)+"ms");
		//log
		return employee;
	}
}