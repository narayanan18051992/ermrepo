package com.erms.jobs.conf;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.erms.batchprocessing.EmployeeItemProcessor;
import com.erms.batchprocessing.EmployeeItemWriter;
import com.erms.model.Employee;



@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	   
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	  
	  
	  @Bean
	  public Job readCSVFilesJob() throws Exception {
	    return jobBuilderFactory
	        .get("readCSVFilesJob")
	        .incrementer(new RunIdIncrementer())
	        .start(step1())
	        .build();
	  }
	  
	  @Bean
	  public Step step1() throws Exception {
	    return stepBuilderFactory.get("step1").<Employee, Employee>chunk(100)
	        .reader(reader())
	        .processor(processor())
	        .writer(writer())
	        .build();
	  }
	  
	 @Bean
	 public FlatFileItemReader<Employee> reader() throws Exception 
	 {
	   FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
		
	   
	   reader.setResource(new FileSystemResource(System.getProperty("input.file.path", "input/employee.csv")));

	   reader.setLinesToSkip(1);   

	   reader.setLineMapper(new DefaultLineMapper() {
	     {

	       setLineTokenizer(new DelimitedLineTokenizer() {
	         {
	           setNames(new String[] { "id", "Name", "dob","rating" });
	         }
	       });
	       
	       setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {
	         {
	           setTargetType(Employee.class);
	         }
	       });
	     }
	   });
		
	   return reader;
	 }
	 
	 @Bean
	 public EmployeeItemProcessor processor() {
	   return new EmployeeItemProcessor();
	 }
	 
	  @Bean
	  public EmployeeItemWriter<Employee> writer() 
	  {
	    return new EmployeeItemWriter<Employee>();
	  }
	 

}
