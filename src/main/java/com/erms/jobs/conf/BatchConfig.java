package com.erms.jobs.conf;

import java.io.File;
import java.io.FileNotFoundException;

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
import com.erms.constants.ERMConstants;
import com.erms.model.Employee;
import com.erms.util.ERMUtil;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job readCSVFilesJob() throws Exception {
		return jobBuilderFactory.get(ERMConstants.READ_FILE_JOB).incrementer(new RunIdIncrementer()).start(step1())
				.build();
	}

	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get(ERMConstants.STEP).<Employee, Employee>chunk(100).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public FlatFileItemReader<Employee> reader() throws Exception { // create bean for batch reader
		ERMUtil.getInstance();
		File inputFile = null;
		FlatFileItemReader<Employee> reader = new FlatFileItemReader<Employee>();
		try {
			inputFile = isValidFilePath(ERMUtil.getProperties().getProperty(ERMConstants.INPUT_FILE_PATH));
		} catch (FileNotFoundException ex) {
			throw ex;
		}
		reader.setResource(new FileSystemResource(inputFile));

		reader.setLinesToSkip(1);

		reader.setLineMapper(new DefaultLineMapper() {
			{

				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(ERMUtil.getProperties().getProperty(ERMConstants.LINE_FIELDS)
								.split(ERMConstants.COMMA_DELIMITER));
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
	public EmployeeItemProcessor processor() { // create bean for batch processor
		return new EmployeeItemProcessor();
	}

	@Bean
	public EmployeeItemWriter<Employee> writer() { // create bean for batch writer
		return new EmployeeItemWriter<Employee>();
	}

	public File isValidFilePath(String path) throws Exception {
		File inputFile = new File(path);
		if (!inputFile.exists()) {
			throw new FileNotFoundException();
		}
		return inputFile;
	}

}
