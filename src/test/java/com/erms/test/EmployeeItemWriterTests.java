package com.erms.test;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.batchprocessing.EmployeeItemWriter;
import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeItemWriterTests {

	private List<Employee> employees;

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	private EmployeeItemWriter<Employee> employeeFileWriter = new EmployeeItemWriter<Employee>();

	@Before
	public void setUp() throws Exception {
		employees = createMockEmployees();
	}

	private List<Employee> createMockEmployees() {
		employees = new ArrayList<Employee>();
		employees.add(ERMConstants.mockEmployee1);
		employees.add(ERMConstants.mockEmployee2);
		return employees;
	}

	@Test
	public void shouldTestNoOfInteractionsOfSaveMethod() throws Exception {
		employeeFileWriter.write(employees);
		verify(employeeService, times(1)).save(employees.get(0));
		verify(employeeService, times(1)).save(employees.get(1));
	}

	@Test(expected = NullPointerException.class)
	public void shouldWriterThrowNullPointerException() throws Exception {
		employeeFileWriter.write(null);
	}

	@Test
	public void shouldTestNoSaveMethodCalledByWriter() throws Exception {
		List<Employee> emp = new ArrayList<Employee>();
		employeeFileWriter.write(emp);
		verify(employeeService, times(0)).save(new Employee());
	}

}
