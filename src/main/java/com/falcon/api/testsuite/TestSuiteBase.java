package com.falcon.api.testsuite;


import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.falcon.api.utility.AppConstants;

public class TestSuiteBase {

	public static PropertiesConfiguration config = null;
	public static PropertiesConfiguration testdata = null;
	public  static ExtentHtmlReporter extentReport = null;
	public  static ExtentReports extent = null;
	public  static ExtentTest report=null;
	public TestSuiteBase() {}
	
	@BeforeMethod
	public void initProperties()throws Exception {
		// Properties Configuration
		config = new PropertiesConfiguration(AppConstants.CONFIG_FILE_PATH);
		testdata = new PropertiesConfiguration(AppConstants.TESTDATA_FILE_PATH);
		//RestAssured.useRelaxedHTTPSValidation();
	}
	
	@BeforeSuite
	public void intiReportProperties(){
		extent =new ExtentReports();
		extentReport=new ExtentHtmlReporter("./TestReports/EmployeeServiceReports.html");
		extent.attachReporter(extentReport);
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			report.fail(MarkupHelper.createLabel(result.getName()+"Test case failed",ExtentColor.RED));
			report.fail(result.getThrowable());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			report.pass(MarkupHelper.createLabel(result.getName()+"Test case passed", ExtentColor.GREEN));
		}
		else
		{
			report.skip(MarkupHelper.createLabel(result.getName()+"Test case skiped", ExtentColor.YELLOW));
			report.skip(result.getThrowable());
		}
	}
	@AfterSuite
	public void tearDown(){
		extent.flush();
	}
	

}
