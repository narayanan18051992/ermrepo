package com.erms.test;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.StepExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.ErmApplication;
import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.repository.EmployeeRepository;
import com.erms.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ErmApplication.class)
@EnableAutoConfiguration
public class EmployeeServiceTests {

	private Employee employee;

	@Autowired
	EmployeeService employeeService;

	private Employee createMockEmployee() {
		return ERMConstants.mockEmployee1;
	}

	@Before
	public void setUp() throws Exception {
		employee = createMockEmployee();
	}

	@Test
	public void shouldReturnNotNullObject() throws Exception {
		Employee created = employeeService.save(employee);
		Assert.assertNotNull(created);
	}

	@Test
	public void shouldEmployeeNameSameForReturnObject() throws Exception {
		Employee created = employeeService.save(employee);
		Assert.assertEquals(employee.getName(),created.getName());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void shouldThrowExceptionForNullInput() throws Exception {
		employeeService.save(null);
	}

}
