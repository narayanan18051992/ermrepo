package com.erms.test;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.erms.constants.ERMConstants;
import com.erms.jobs.conf.BatchConfig;
import com.erms.util.ERMUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class BatchConfigTests {

	@Mock
	Properties prop;

	@Mock
	InputStream input;

	@InjectMocks
	private BatchConfig batchConfig = new BatchConfig();

	@Before
	public void setUp() throws Exception {
		ERMUtil.getInstance();
	}

	@Test
	public void shouldReaderNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.reader());
	}

	@Test
	public void shouldProcessorNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.processor());
	}

	@Test
	public void shouldWriterNotNull() throws UnexpectedInputException, ParseException, Exception {
		Assert.assertNotNull(batchConfig.writer());
	}

	@Test(expected = FileNotFoundException.class)
	public void shouldThrowFileNotFoundException() throws Exception {
		batchConfig.isValidFilePath(ERMConstants.MOCK_FILE_PATH);
	}

}
