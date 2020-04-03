package com.falcon.api.post.testscripts.employeeservice;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;

import com.falcon.api.testsuite.TestSuiteBase;
import com.falcon.api.utility.EmployeeServiceHelper;



public class TC_001_Verify_To_Create_Employee_With_Valid_Data extends TestSuiteBase {
	Map<String, String> headers = new HashMap<>();
	String url = null;

	@Test
	public void addEmployeeWithValidData() throws Exception {
		report = extent.createTest("TC_001_Verify_To_Create_Employee_With_Valid_Data", "Employee-Service");
		report.info("Verify to create Employee with valid data");
		EmployeeServiceHelper.addEmployeeWithValidDataAndReturnEmployeeID();
		
	}
}
