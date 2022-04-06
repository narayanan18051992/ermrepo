package com.erms;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import com.erms.constants.ERMConstants;

@SpringBootApplication
@EnableScheduling
public class ErmApplication {

	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job job;
	private JobParameters params;

	public static void main(String[] args) {
		SpringApplication.run(ErmApplication.class, args);
	}

	@Scheduled(cron = "0 */1 * * * ?")   // scheduler will be started every 1 min 
	public void perform() throws Exception {
		params = new JobParametersBuilder().addString(ERMConstants.JobID, String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
	}

	public JobParameters getParams() {
		return params;
	}

}
