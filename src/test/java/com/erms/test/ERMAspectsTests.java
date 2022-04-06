package com.erms.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.aspectj.lang.JoinPoint;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.TooManyActualInvocations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import com.erms.aspect.ERMAspect;


public class ERMAspectsTests {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	private JoinPoint joinPoint;

	@InjectMocks
	private ERMAspect ermAspect = new ERMAspect();

	@Test
	public void shouldCheckNoOfInteractionWithlogEmployeeItemWriterError() throws Throwable {
		ermAspect.logEmployeeItemWriterError(joinPoint, new Exception());
		ermAspect.logEmployeeItemWriterError(joinPoint, new Exception());
		verify(joinPoint, times(2)).getSignature();
	}

	@Test(expected = TooManyActualInvocations.class)
	public void shouldThrowTooManyActualInvocationsInlogEmployeeItemWriterError() throws Throwable {
		ermAspect.logEmployeeItemWriterError(joinPoint, new Exception());
		ermAspect.logEmployeeItemWriterError(joinPoint, new Exception());
		verify(joinPoint, times(1)).getSignature();
	}

	@Test
	public void shouldCheckNoInteractionWithMethodLogAfterPeformScheduling() throws Throwable {
		ermAspect.logAfterPeformScheduling(joinPoint);
		verify(joinPoint, times(0)).getSignature();
	}

	@Test
	public void shouldCheckNoInteractionWithMethodLogAfterProcessor() throws Throwable {
		ermAspect.logAfterProcessor(joinPoint);
		verify(joinPoint, times(0)).getSignature(); 
	}

	@Test
	public void shouldCheckNoInteractionWithMethodLogAfterWriter() throws Throwable {
		ermAspect.logAfterWriter(joinPoint);
		verify(joinPoint, times(0)).getSignature(); 
	}

}
