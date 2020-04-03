package com.falcon.api.utility;

import java.io.File;

public class AppConstants {
	public static final String USER_DIR = System.getProperty("user.dir");
	public static final String TESTDATA_FOLDERPATH = USER_DIR + File.separator + "src" + File.separator + "main"
			+ File.separator + "java" + File.separator + "com" + File.separator + "falcon" + File.separator
			+ "api" + File.separator + "testdata";
	public static final String CONFIG_FILE_PATH = USER_DIR + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources" + File.separator + "config.properties";
	public static final String TESTDATA_FILE_PATH = USER_DIR + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources" + File.separator + "testdata.properties";
	public static final String TEST_CRED_FILE_PATH = TESTDATA_FOLDERPATH + File.separator + "employeeservice"
			+ File.separator + File.separator + "TestCredentials.json";
	public static final String CREATE_EMPLOYEE = TESTDATA_FOLDERPATH + File.separator + "employeeservice"
			+ File.separator + "addemployee.json";
	
	public static final String CREATE_SCHEDULE_JSON_FILE = TESTDATA_FOLDERPATH + File.separator
			+ "employeeservice" + File.separator + File.separator + "AddSchedule.json";
	public static final String CREATE_INVALID_SCHEDULE_JSON_FILE = TESTDATA_FOLDERPATH + File.separator
			+ "employeeservice" + File.separator + File.separator + "InvalidSchedulePayload.json";


}
