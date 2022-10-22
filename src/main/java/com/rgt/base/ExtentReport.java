package com.rgt.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReport {

	public static void main(String[] args) {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("./src/test/java/com/rgt/reports/Report.html").viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD,ViewName.TEST,ViewName.CATEGORY}).apply();
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Web UI Test");
		spark.config().setReportName("RatnaGlobalTech");
		extent.attachReporter(spark);
		extent.createTest("MyFirstTest").log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		extent.addTestRunnerOutput("");
		
		//extent.flush();

		

	}

}
