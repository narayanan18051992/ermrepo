package com.erms.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.erms.ErmApplication;

@Aspect
@Component
public class ERMAspect {

	private static final Logger logger = LoggerFactory.getLogger(ErmApplication.class);

	@AfterThrowing(value = "execution(* com.erms..*.*(..))", throwing = "ex") // method will be called after exception
																				// is thrown
	public void logEmployeeItemWriterError(JoinPoint joinPoint, Exception ex) {
		logger.error("Throwing exception in method:" + joinPoint.getSignature());
		logger.error("Exception is:" + ex.getMessage());
	}

	@After("execution(* com.erms.ErmApplication.perform(..))") // method will be called after scheduler method executed
	public void logAfterPeformScheduling(JoinPoint joinPoint) {
		logger.info("Scheduler Started : " + new Date());
	}

	@After("execution(* com.erms.jobs.conf.BatchConfig.reader(..))") // method will be called after reader method reader method invoked
	public void logAfterReader(JoinPoint joinPoint) {
		logger.info("Read Completed ");
	}

	@After("execution(* com.erms.batchprocessing.EmployeeItemProcessor.process(..))") // method will be called after process method invoked
	public void logAfterProcessor(JoinPoint joinPoint) {
		logger.info("Process Completed ");
	}

	@After("execution(* com.erms.batchprocessing.EmployeeItemWriter.write(..))") // method will be called after write method invoked
	public void logAfterWriter(JoinPoint joinPoint) {
		logger.info("Write Completed");
	}

}
