package com.erms.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import com.erms.constants.ERMConstants;

public class ERMUtil {

	private static Properties prop = new Properties();

	private static ERMUtil ermUtil = new ERMUtil();

	private static InputStream input;

	public static ERMUtil getInstance() throws Exception {
		try {
			input = new FileInputStream(
					System.getProperty(ERMConstants.PROPERTIES_FILE_PATH, "") + ERMConstants.PROPERTIES_FILE_NAME);
			prop.load(input);
		}catch(FileNotFoundException e) {
			throw e;
		}finally {
			input.close();
		}
		return ermUtil;
	}

	public static Properties getProperties() {
		return prop;
	}

}
