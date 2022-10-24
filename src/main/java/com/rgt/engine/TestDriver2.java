package com.rgt.engine;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.rgt.base.Base;
import com.rgt.library.Library;
import com.rgt.utils.ExcelUtils;

public class TestDriver2 
{
	public Library library;
	public WebDriver driver;
	public Properties prop;
	public ExcelUtils excel;
	public Base base;
	public WebElement element;
	ExtentReports extentreport;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	public final String SCENARIO_SHEET_PATH = System.getProperty("user.dir")+"/resources/datafiles/TC_Master.xlsx";
	public final String ExtentReport_Path = System.getProperty("user.dir")+"/src/test/java/com/rgt/reports/Report.html";
	
	public void startExecution() throws IOException, InterruptedException {
		library= new Library();
		extentreport = new ExtentReports();
		spark = new ExtentSparkReporter(ExtentReport_Path).viewConfigurer().viewOrder().as(new ViewName[] {ViewName.TEST,ViewName.DASHBOARD,ViewName.CATEGORY}).apply();
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Web UI Test");
		spark.config().setReportName("RatnaGlobalTech");
		extentreport.attachReporter(spark);
		excel = new ExcelUtils(SCENARIO_SHEET_PATH);
		int count =excel.getTestdata().size();
		int testCaseCount = excel.getTCMaster().size();
		Set<String> testCaseNames = new HashSet<String>();
	
		for(int j=0;j<testCaseCount;j++) {
			
			testCaseNames.add(excel.getTCMaster().get(j).getTestCase());
			extentTest=extentreport.createTest(excel.getTCMaster().get(j).getTestCase());

		for (int i = 0; i < count; i++) {	
				
			if(excel.getTCMaster().get(j).getTestCase()==excel.getTestdata().get(i).getTestCase()) {
					Set<String> tc= new HashSet<>();
					tc.add(excel.getTestdata().get(i).getSteps());
					int stepsCount= tc.size();
							
					for (int k = 0; k < stepsCount; k++) {
			
						
						String locatorType = excel.getTestdata().get(i).getLocatorType().trim();
						String locatorValue = excel.getTestdata().get(i).getLocatorValue().trim();
						String action = excel.getTestdata().get(i).getAction().trim();
						String value = excel.getTestdata().get(i).getInputValue().trim();
						String steps = excel.getTestdata().get(i).getTestSteps().trim();
						try {		
								
							if(action.equalsIgnoreCase("OPEN_BROWSER")) {
								
									base = new Base();
									prop =base.init_properties();
									if (value.isEmpty() || value.equals("NA")) {
										driver = base.init_driver(prop.getProperty("browser"));
										extentTest.info(steps+"--"+prop.getProperty("browser"));
										System.out.println(steps);
										
									} else {
										
										driver = base.init_driver(value);
										extentTest.info(steps+"--"+value);
										System.out.println(steps +"--"+ value);
									}
								}else if(action.equalsIgnoreCase("CLOSE_BROWSER")) {
									driver.quit();
									extentTest.info(steps);
									System.out.println(steps +"--"+ value);
									
								}else if(action.equalsIgnoreCase("ENTER_URL")) {
									if (value.isEmpty() || value.equals("NA")) {
										driver.get(prop.getProperty("url"));
										extentTest.info(steps+"--"+prop.getProperty("browser"));
										System.out.println(steps +"--"+ value);
									} else {
										driver.get(value);
										extentTest.info( steps +"--"+ value);
										System.out.println(steps +"--"+ value);
									}
									
								}else if(action.equalsIgnoreCase("WAIT")) {
									Thread.sleep(Integer.parseInt(value));
									extentTest.info(steps+"--10 secs");
									System.out.println(steps +"--"+ value);
								}else if(action.equalsIgnoreCase("ENTER")) {
									library.enterValue(driver,locatorType, locatorValue, value);
									System.out.println(steps +"--"+ value);
								}else if(action.equalsIgnoreCase("ISDISPLAYED")) {
		
									Boolean b=library.isDisplayed(driver,locatorType, locatorValue, value);
									if(b) {
										extentTest.info(steps+"--"+value);
									}else {
										extentTest.fail(steps+"--"+b);
									}
									
									//System.out.println(steps +"--"+ value);
								}else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
									library.explicitWait(driver, locatorType, locatorValue,value);
									System.out.println(steps +"--"+ value);
								}else if(action.equalsIgnoreCase("VERIFYTEXT")) {
									String text = library.verifyText(driver, locatorType, locatorValue, value);
									if(value.contentEquals(text)) {
										extentTest.info(steps+"--"+"Actual:"+text+"--Expected:"+value);
									}else {
										extentTest.fail(steps+"--"+"Actual:"+text+"--Expected:"+value);
									}
					
									System.out.println(steps +"--"+ value);
								} 
								
								
		
					
							}
					catch(Exception e) {
						extentTest.fail(steps+"--"+e);
					}
				}
				extentreport.flush();
			}
		}
			}	
		}
}

