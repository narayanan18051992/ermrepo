package com.erms.test;

import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.erms.batchprocessing.EmployeeItemProcessor;
import com.erms.batchprocessing.EmployeeItemWriter;
import com.erms.jobs.conf.BatchConfig;
import com.erms.model.Employee;
import com.erms.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ERMApplicationTests {
	
	private Employee employee;
	
	@Mock
    protected StepExecution stepExecution;
	
	@Mock
    protected JobParameters jobParams;
	
	@InjectMocks
    private EmployeeItemProcessor employeeFileProcessor =
    new EmployeeItemProcessor();
	
	@InjectMocks
	private EmployeeItemWriter employeeItemWriter=
	new EmployeeItemWriter();
	
	@InjectMocks
	private BatchConfig batchConfig=
	new BatchConfig();
	
	
	
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		Date fromDate=new SimpleDateFormat("dd-MM-yyyy").parse("31-03-2022");  
		Date toDate=new SimpleDateFormat("dd-MM-yyyy").parse("31-03-2022");   
        when(this.stepExecution.getJobParameters()).thenReturn(this.jobParams);
        when(this.jobParams.getDate("fromDate")).thenReturn(fromDate);
        when(this.jobParams.getDate("toDate")).thenReturn(toDate);
        employee = createMockEmployee();
       
	
	}
	
	private Employee createMockEmployee() {
        Employee e = new Employee();
        e.setId(1);
        e.setName("Naryanan");
        e.setDob("18-05-1992");
        e.setRating(95);
        return e;
    }
	

	@Test
	public void testReaderNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.reader());
	}
	
	
	
	@Test
	public void testProcessorNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.processor());
	}
	
	@Test
	public void testProcessorShouldReturnEmployeeObject() throws Exception{
			Employee emp=employeeFileProcessor.process(employee);
			Assert.assertNotNull(emp);
	}
	
	
	@Test
	public void shouldReturnAgeCategorySeniorEmployee() throws Exception{
			Employee emp=employeeFileProcessor.process(employee);
			Assert.assertEquals(emp.getAgeCategory(), "Senior Employee");
	}
	
	
	@Test
	public void shouldNotReturnAgeCategorySeniorEmployee() throws Exception{
			employee.setDob("19-07-1996");
			Employee emp=employeeFileProcessor.process(employee);
			Assert.assertNotEquals(emp.getAgeCategory(), "Senior Employee");
		
	}
	
	
	@Test
	public void shouldReturnPerformanceLevelHighPerformer() throws Exception{
			Employee emp=employeeFileProcessor.process(employee);
			Assert.assertEquals(emp.getPerformanceLevel(), "High Performer");
	}
	
	
	@Test
	public void shouldNotReturnPerformanceLevelHighPerformer() throws Exception{
			employee.setRating(85);
			Employee emp=employeeFileProcessor.process(employee);
			Assert.assertNotEquals(emp.getPerformanceLevel(), "High Performer");
	}
	
	
	@Test(expected=java.text.ParseException.class)
	public void shouldThrowParsingException() throws Exception {
		employee.setDob("01012995");
		Employee emp=employeeFileProcessor.process(employee);
	}
	
	
	
	@Test
	public void testWriterNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.writer());
	}
	

}
