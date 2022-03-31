package com.erms.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.erms.ErmApplication;

@Aspect  
@Component
public class ERMAspect {

	
	private static final Logger logger = LoggerFactory.getLogger(ErmApplication.class);
	
	@AfterThrowing(value="execution(* com.erms..*.*(..))",throwing="ex")  
	public void logEmployeeItemWriterError(JoinPoint joinPoint, Exception ex)  
	{  
		
		logger.error("Throwing exception in method:"+joinPoint.getSignature());  
		logger.error("Exception is:"+ex.getMessage());  
	}     
	
	
	
	@After("execution(* com.erms.ErmApplication.perform(..))")     
    public void logBeforePeformScheduling(JoinPoint joinPoint)
    {
        logger.info("Scheduler Started : " + new Date());
    }
	
	
	@After("execution(* com.erms.jobs.conf.BatchConfig.reader(..))")     
    public void logBeforeReader(JoinPoint joinPoint)
    {
        logger.info("Read Completed ");
    }
	
	
	@After("execution(* com.erms.batchprocessing.EmployeeItemProcessor.process(..))")     
    public void logBeforeProcessor(JoinPoint joinPoint)
    {
		
        logger.info("Process Completed ");
    }
	
	
	@After("execution(* com.erms.batchprocessing.EmployeeItemWriter.write(..))")     
    public void logBeforeWriter(JoinPoint joinPoint)
    {
        logger.info("Write Completed");
        
    }
	
}
