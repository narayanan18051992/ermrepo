package com.erms.batchprocessing;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.batch.item.ItemProcessor;
import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.util.ERMUtil;

public class EmployeeItemProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(final Employee employee) throws Exception {     //processor validate input details
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
		return employee;
	}
}