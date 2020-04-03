package com.falcon.api.utility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.falcon.api.testsuite.TestSuiteBase;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class Util extends TestSuiteBase {

	/*
	 * This is the Utility method which returns full URL by combining Base URI
	 * and endpoint.
	 */
	public static String buildURI(String endPoint) {
		String baseURI = config.getString("BaseURI");
		String requestURI = baseURI + endPoint;
		return requestURI;
	}

	// This is utility Method which returns JSONObject from String
	// representation of JSON.
	public static JSONObject getJSONObjectfromString(String json) throws ParseException {
		return (JSONObject) new JSONParser().parse(json);
	}

	public static JSONObject getJSONObjectFromFilePath(String filePath)
			throws FileNotFoundException, IOException, ParseException {
		return (JSONObject) new JSONParser().parse(new FileReader(filePath));
	}

	public static String getAccessToken() throws FileNotFoundException, IOException, ParseException {
		String token="Bearer ";
		JSONObject authenticationCredentials = getJSONObjectFromFilePath(AppConstants.TEST_CRED_FILE_PATH);
		Response response = RestAssured.given().contentType(ContentType.JSON).body(authenticationCredentials).when()
				.post(buildURI(config.getString("login"))).andReturn();
		return token+getJSONObjectfromString(response.getBody().asString()).get("accessToken");
	}
	
	public static JSONArray getJSONArrayFromString(String json) throws ParseException{
		return (JSONArray)new JSONParser().parse(json);
	}
	
	public static JSONObject getFirstJsonObject(JSONArray jsonArray) throws Exception{
        return (JSONObject) jsonArray.stream().findFirst().get();
    }
	
	
	public static JSONObject getAllJsonObjects(JSONArray jsonArray) throws Exception{
        return (JSONObject) jsonArray.stream().findAny().get();
    }

	public static long getRandomNumber() {	
		return Math.round(Math.random() * 100000);
	}
	
	public static JSONArray getJSONArrayFromFilePath(String filePath)throws FileNotFoundException, IOException, ParseException{
        return  (JSONArray)new JSONParser().parse(new FileReader(filePath));
    }
}
