package com.erms;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.Instant;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.erms.constants.ERMConstants;
import com.erms.service.AppRunner;
import com.erms.service.CSVFileProcessor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ErmApplication implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(ErmApplication.class);

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	CSVFileProcessor csvFileProcessor;
	
	private JobParameters params;

	public static void main(String[] args) {
		SpringApplication.run(ErmApplication.class, args);
	}

	@Scheduled(cron = "${scheduler.cron}")  
	public void perform() throws Exception {
		params = new JobParametersBuilder().addString(ERMConstants.JOB_ID, String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
	}

	public JobParameters getParams() {
		return params;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//CSVFileProcessor logFileProcessor = run.getBean(CSVFileProcessor.class);
		Instant start = Instant.now();	
		csvFileProcessor.processFile("C:\\Users\\lm9\\06042022workspace\\ermrepo\\input\\sample-data.csv");		
		
		Instant end = Instant.now();		
		
		logger.info("Time Taken to Process File {} Minutes",java.time.Duration.between(end, start).toMinutes());
		
	}
	
//	@Bean
//	  public Executor taskExecutor() {
//	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//	    executor.setCorePoolSize(2);
//	    executor.setMaxPoolSize(3);
//	    executor.setQueueCapacity(500);
//	    executor.setThreadNamePrefix("AsyncProcessor-");
//	    executor.initialize();
//	    return executor;
//	  }

}
