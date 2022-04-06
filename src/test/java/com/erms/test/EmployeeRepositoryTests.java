package com.erms.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class EmployeeRepositoryTests {

	private Employee employee;

	private Employee createMockEmployee() {
		return ERMConstants.mockEmployee1;
	}

	@Before
	public void setUp() throws Exception {
		employee = createMockEmployee();
	}

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void shouldReturnObjectNotNull() throws Exception {
		Employee returnValue = employeeRepository.save(employee);
		Assert.assertNotNull(returnValue);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void shouldTestWithNullValues() throws Exception {
		employeeRepository.save(ERMConstants.mockEmployee5);
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void shouldRepositoryThrowsException() throws Exception {
		employeeRepository.save(null);
	}

}
