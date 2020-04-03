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

public class TC_005_Verify_To_Get_All_Employees extends TestSuiteBase{
	static Map<String, String> headers = new HashMap<>();
	static String url = null;
	
	@Test
	public void getAllEmployees() throws Exception {
	report = extent.createTest("TC_005_Verify_To_Get_All_Employees", "Employee-Service");
	report.info("Verify to Get All Employees");		
	url = Util.buildURI(config.getString("getListOfEmployees"));				
	report.info("Get the added Employees");	
	try {
	Response response = given()
			.headers(headers)
			.contentType(ContentType.JSON)
			.get(url)
			.andReturn();
	report.info("Verify the status code:" + response.statusCode());
	assertEquals(response.getStatusCode(), STATUS_CODE.STATUS_200.getValue(),
			"ERROR: Status Code Validation Failed.");
	if (response.getStatusCode() == STATUS_CODE.STATUS_200.getValue()) {
		report.info( "Status Code Validated Sucessfully as :" + response.getStatusCode());
	} else {
		report.info( "Status Code Validation Failed :" + response.getStatusCode());
	}
	
	} catch(Exception ex){
		ex.printStackTrace();
		
	}
	}
	
}
