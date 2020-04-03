package com.falcon.api.utility;


import static com.jayway.restassured.RestAssured.given;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;
import com.falcon.api.testsuite.TestSuiteBase;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import groovy.json.internal.JsonStringDecoder;
import junit.framework.Assert;

@SuppressWarnings("unused")
public class EmployeeServiceHelper extends TestSuiteBase{

	static Map<String, String> headers= new HashMap<>();
	static String url=null;

	public static boolean deleteEmployee() throws Exception
	{
		report.info("Delete Employee");
		return RestAssured.given().headers(headers)
				.pathParam("id", testdata.getString("id"))
				.when()
				.delete(Util.buildURI(config.getString("deleteEmployee"))).getStatusCode()==STATUS_CODE.STATUS_200.getValue();
	
	}
	
	public static void deleteEmployeeById() throws Exception{
		report.info("Delete Employee");
		Response response = given().headers(headers).contentType(ContentType.JSON)
				.pathParam("id", testdata.getString("id")).delete(Util.buildURI(config.getString("deleteEmployee")))
				.andReturn();
		report.info("Validsting status code");
		assertEquals(response.getStatusCode(), STATUS_CODE.STATUS_200.getValue(),
				"ERROR: Status Code Validation Failed.");
		if (response.getStatusCode() == STATUS_CODE.STATUS_200.getValue()) {
			report.log(Status.INFO, "Status Code Validated Sucessfully as :" + response.getStatusCode());
		} else {
			report.log(Status.ERROR, "Status Code Validation Failed :" + response.getStatusCode());
		}

	}


	public static void addEmployeeWithValidDataAndReturnEmployeeID() throws Exception, FileNotFoundException{
		report.info("Initiating Employee Creation");
		url=Util.buildURI(config.getString("addEmployee"));
		report.info("Getting TestData for Employee");
		String name="TestEmployee_"+Util.getRandomNumber();
		JSONObject employeeObject= Util.getJSONObjectFromFilePath(AppConstants.CREATE_EMPLOYEE);
		employeeObject.put("name",name);	
		try {
		Response response = given()
				.headers(headers)
				.contentType(ContentType.JSON)
				.body(employeeObject)
				.post(url)
				.andReturn();
		report.info("Validating Status Code:" + response.getStatusCode());
		
		//Validation of status code		
		Assert.assertEquals(response.getStatusCode(), STATUS_CODE.STATUS_200.getValue());
		if (response.getStatusCode() == STATUS_CODE.STATUS_200.getValue()) {
			report.log(Status.INFO, "Status Code Validated Sucessfully as :" + response.getStatusCode());
		} else {
			report.log(Status.ERROR, "Status Code Validation Failed :" + response.getStatusCode());
		}
		
		JSONObject responseObject= Util.getJSONObjectfromString(response.getBody().asString());
		JSONObject data = (JSONObject) responseObject.get("data");
		Object id = data.get("id");
		//Validation of response body		
		report.info("Validation response body:" + name);
		Assert.assertEquals(name, (String) data.get("name"));		
		String stringmessage = response.getStatusCode() == 200 ? "INFO: Employee Created Successfully with ID=" + id:"ERROR: Employee creation is failed";
	    report.info(stringmessage);
		//Validation of Headers
		report.info("Validating headers");
	    String contenttype = response.header("Content-Type");
		Assert.assertEquals(contenttype, "application/json;charset=utf-8");		
		String server =	response.header("Server");
		Assert.assertEquals(server, "nginx/1.16.0");	

		report.info("Stroing test data values");
		testdata.setProperty("id", id);
		testdata.save();
		testdata.setProperty("name",name);
		testdata.save();
		
} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	public static JSONObject getJSONObjectOfEmployee() throws Exception {
		report.info("Getting employee id");
		url=Util.buildURI(config.getString("getEmployeeById"));
	    
		Response response = given()
				.headers(headers)
				.contentType(ContentType.JSON)
				.pathParam("id", testdata.getString("id"))
				.get(url)
				.andReturn();
		JSONObject responseObj = Util.getJSONObjectfromString(response.getBody().asString());
		return responseObj;

	}
}

