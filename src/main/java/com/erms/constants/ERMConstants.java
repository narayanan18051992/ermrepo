package com.erms.constants;

import com.erms.model.Employee;

public class ERMConstants {

	public static final String DT_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String EMP_AGE_CATEGORY="emp.age.category";
	public static final String EMP_PERFORMANCE_LEVEL="emp.performance.level";
	public static final String EMP_DOB="emp.dob";
	public static final String SCHEDULER_CRON="scheduler.cron";
	public static final String JobID="JobID";
	public static final String PROPERTIES_FILE_PATH="properties.file.path";
	public static final String PROPERTIES_FILE_NAME="ermconfig.properties";
	public static final String INPUT_FILE_PATH="input.file.path";
	public static final String LINE_FIELDS="line.fields";
	public static final String AGE_CATEOGRY="Senior Employee";
	public static final String PERFORMANCE_LEVEL="High Performer";
	public static final String READ_FILE_JOB="readCSVFilesJob";
	public static final String STEP="step1";
	public static final String COMMA_DELIMITER=",";
	public static final Employee mockEmployee1=new Employee(1, "Venkat", "13-07-1989", 95);
	public static final Employee mockEmployee2=new Employee(2, "sai", "22-05-1989", 89);
	public static final Employee mockEmployee3=new Employee(3, "ajay", "12-04-1996", 97);
	public static final Employee mockEmployee4=new Employee(4, "rakesh", "12-04-1994", 97);
	public static final Employee mockEmployee5=new Employee(1, null, null, 67);
	public static final String MOCK_DOB="19-07-1994";
	public static final String MOCK_UNFORMATTED_DOB="01012995";
	public static final int MOCK_RATING=85;
	public static final int MOCK_ID=2;
	public static final String MOCK_NAME="venkat";
	public static final int PERFORMANCE_THRESHOLD=90;
	public static final String MOCK_FILE_PATH="/mockpath";

}
