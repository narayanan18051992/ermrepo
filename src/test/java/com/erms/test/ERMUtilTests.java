package com.erms.test;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erms.constants.ERMConstants;
import com.erms.util.ERMUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ERMUtilTests {

	@Mock
	Properties prop;

	@Before
	public void setUp() throws Exception {
		ERMUtil.getInstance();
		ERMUtil.getProperties().setProperty(ERMConstants.EMP_DOB, ERMConstants.MOCK_DOB);
	}

	@Test
	public void shouldGetInstanceObjectNotNull() throws Exception {
		Assert.assertNotNull(ERMUtil.getInstance());
	}

	@Test
	public void shouldValueNotNullWithKey() throws Exception {
		String expectedValue = ERMUtil.getProperties().getProperty(ERMConstants.EMP_DOB);
		Assert.assertNotNull(expectedValue);
	}

	@Test
	public void shouldReturnExpectedValue() throws Exception {
		Assert.assertEquals(ERMConstants.MOCK_DOB, ERMUtil.getProperties().getProperty(ERMConstants.EMP_DOB));
	}

	@Test
	public void shouldReturnNullValueForInvalidKey() throws Exception {
		Assert.assertNull(ERMUtil.getProperties().getProperty(ERMConstants.MOCK_NAME));
	}

	@Test(expected = FileNotFoundException.class)
	public void shouldReturnExceptionWithKey() throws Exception {
		System.setProperty(ERMConstants.PROPERTIES_FILE_PATH, ERMConstants.MOCK_FILE_PATH);
		ERMUtil.getInstance();
	}

}
