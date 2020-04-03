package com.falcon.api.put.testscripts.employeeservice;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.falcon.api.testsuite.TestSuiteBase;
import com.falcon.api.utility.STATUS_CODE;
import com.falcon.api.utility.Util;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TC_006_Update_Employee_With_Valid_Data extends TestSuiteBase {
	static Map<String, String> headers = new HashMap<>();
	static String url = null;

	@Test
	public void updateEmployeeWithValidData() throws Exception {
		report = extent.createTest("TC_006_Update_Employee_With_Valid_Data", "Employee-Service");
		report.info("Verify to update Employee with valid data");
		url = Util.buildURI(config.getString("updateEmployee"));		
		String requestBody = "{\r\n" +
	            " \"name\":\"put_test_employee\",\r\n" +
	            " \"salary\":\"1123\",\r\n" +
	            " \"age\":\"23\"\r\n" +
	            "}";		
	
	    Response response = given().headers(headers).contentType(ContentType.JSON).pathParam("id", testdata.getString("id")).body(requestBody).put(url)
				.andReturn();
		report.info("Validating Status Code");
		assertEquals(response.getStatusCode(), STATUS_CODE.STATUS_200.getValue(), "Status Code Validation Failed.");	
	
	}
	
}
