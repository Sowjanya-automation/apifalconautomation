package com.falcon.api.get.testscripts.employeeservice;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.falcon.api.testsuite.TestSuiteBase;
import com.falcon.api.utility.STATUS_CODE;
import com.falcon.api.utility.Util;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TC_003_Verify_To_Get_Employees_Without_ID extends TestSuiteBase{
	static Map<String, String> headers = new HashMap<>();
	static String url = null;
	
	@Test
	public void getEmployeeWithoutID() {
		report = extent.createTest("TC_003_Verify_To_Get_Employees_Without_ID", "Employee-Service");
		report.info("Verify to Get Employee without id");		
		url = Util.buildURI(config.getString("getEmployeeById"));				
		Response response = given()
				.headers(headers)
				.contentType(ContentType.JSON)
				.pathParam("id", "")
				.get(url)
				.andReturn();
		report.info("Status code:" + response.getStatusCode());
		report.info("Verify the status code:" + response.statusCode());
		assertEquals(response.getStatusCode(), STATUS_CODE.STATUS_404.getValue(),
				"ERROR: Status Code Validation Failed.");
		if (response.getStatusCode() == STATUS_CODE.STATUS_404.getValue()) {
			report.log(Status.INFO, "Status Code Validated Sucessfully as :" + response.getStatusCode());
		} else {
			report.log(Status.ERROR, "Status Code Validation Failed :" + response.getStatusCode());
		}
	}

}
