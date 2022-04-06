package com.erms.test;

import java.util.Objects;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.erms.batchprocessing.EmployeeItemProcessor;
import com.erms.constants.ERMConstants;
import com.erms.jobs.conf.BatchConfig;
import com.erms.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class EmployeeItemProcessorTests {

	@InjectMocks
	private EmployeeItemProcessor employeeFileProcessor = new EmployeeItemProcessor();

	@Test
	public void shouldReturnObjectNotNull() throws Exception {
		Employee emp = employeeFileProcessor.process(ERMConstants.mockEmployee1);
		Assert.assertNotNull(emp);
	}

	@Test
	public void shouldReturnAgeCategorySeniorEmployee() throws Exception {
		Employee emp = employeeFileProcessor.process(ERMConstants.mockEmployee4);
		Assert.assertEquals(ERMConstants.AGE_CATEOGRY, emp.getAgeCategory());
	}

	@Test
	public void shouldNotReturnAgeCategorySeniorEmployee() throws Exception {
		Employee emp = employeeFileProcessor.process(ERMConstants.mockEmployee3);
		Assert.assertNotEquals(ERMConstants.AGE_CATEOGRY,emp.getAgeCategory());
	}

	@Test
	public void shouldReturnPerformanceLevelHighPerformer() throws Exception {
		Employee emp = employeeFileProcessor.process(ERMConstants.mockEmployee3);
		Assert.assertEquals(ERMConstants.PERFORMANCE_LEVEL,emp.getPerformanceLevel());
	}

	@Test
	public void shouldNotReturnPerformanceLevelHighPerformer() throws Exception {
		Employee emp = employeeFileProcessor.process(ERMConstants.mockEmployee2);
		Assert.assertNotEquals(ERMConstants.PERFORMANCE_LEVEL,emp.getPerformanceLevel());
	}

	@Test(expected = java.text.ParseException.class)
	public void shouldThrowParsingException() throws Exception {
		Employee actualInput = new Employee();
		actualInput.setDob(ERMConstants.MOCK_UNFORMATTED_DOB);
		employeeFileProcessor.process(actualInput);
	}
}
