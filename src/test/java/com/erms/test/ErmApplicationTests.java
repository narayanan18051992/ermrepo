package com.erms.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.ErmApplication;
import com.erms.constants.ERMConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ErmApplicationTests {

	@Mock
	private JobLauncher jobLauncher;

	@Mock
	Job job;

	@InjectMocks
	private ErmApplication ermApplication = new ErmApplication();
	
	
	@Test
	public void shouldCheckGetParamNotNull() throws Exception {
		ermApplication.perform();
		Assert.assertNotNull(ermApplication.getParams());
	}

	@Test
	public void shouldSchedulerCallingJobLauncherMethod() throws Exception {
		ermApplication.perform();
		verify(jobLauncher, times(1)).run(job, ermApplication.getParams());
	}

	@Test
	public void shouldJobLauncherRunMethodNotExecuted() throws Exception {
		JobParameters params = new JobParametersBuilder()
				.addString(ERMConstants.JobID, String.valueOf(System.currentTimeMillis())).toJobParameters();
		verify(jobLauncher, times(0)).run(job, params);
	}

}
