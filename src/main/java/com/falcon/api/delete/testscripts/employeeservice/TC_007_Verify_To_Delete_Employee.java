package com.falcon.api.delete.testscripts.employeeservice;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import com.falcon.api.testsuite.TestSuiteBase;
import com.falcon.api.utility.EmployeeServiceHelper;

public class TC_007_Verify_To_Delete_Employee extends TestSuiteBase {
	Map<String, String> headers = new HashMap<>();
	String url = null;

	@Test
	public void deleteEmployeeWithValidData() throws Exception {
		report = extent.createTest("TC_007_Verify_To_Delete_Employee", "Employee-Service");
		report.info("Verify to delete created Employee");
		EmployeeServiceHelper.deleteEmployeeById();
		
		
	}
}
