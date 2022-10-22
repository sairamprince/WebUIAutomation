package com.rgt.engine;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.rgt.base.Base;
import com.rgt.utils.ExcelUtils;

public class TestDriver 
{
	public WebDriver driver;
	public Properties prop;
	public ExcelUtils excel;
	public Base base;
	public WebElement element;
	ExtentReports extentreport;
	ExtentSparkReporter spark;
	ExtentTest extentTest;
	public final String SCENARIO_SHEET_PATH = System.getProperty("user.dir")+"/resources/datafiles/TC_Master.xlsx";
	public final String ExtentReport_Path = System.getProperty("user.dir")+"/src/test/java/com/rgt/reports/WebAutomationReport.html";
	
	public void startExecution() throws IOException {
		extentreport = new ExtentReports();
		spark = new ExtentSparkReporter(ExtentReport_Path).viewConfigurer().viewOrder().as(new ViewName[] {ViewName.TEST,ViewName.DASHBOARD,ViewName.CATEGORY}).apply();
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Web UI Test");
		spark.config().setReportName("RatnaGlobalTech");
		extentreport.attachReporter(spark);
		//ExtentTest	extentTest = extentreport.createTest("Hello");
		excel = new ExcelUtils(SCENARIO_SHEET_PATH);
		int count =excel.getTestdata().size();
		int testCaseCount = excel.getTCMaster().size();
		Set<String> testCaseNames = new HashSet<String>();
		for(int j=0;j<testCaseCount;j++) {
			
			testCaseNames.add(excel.getTCMaster().get(j).getTestCase());
			System.out.println(testCaseNames);
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
						switch (action) {
						case "OPEN_BROWSER":
							base = new Base();
							prop =base.init_properties();
							if (value.isEmpty() || value.equals("NA")) {
								driver = base.init_driver(prop.getProperty("browser"));
								extentTest.info(steps+"--"+prop.getProperty("browser"));
								//extentreport.createTest(excel.getTestdata().get(i).getTestCse()).log(Status.PASS, steps);
								System.out.println(steps);
								
							} else {
								
								driver = base.init_driver(value);
								extentTest.info(steps+"--"+value);
								System.out.println(steps +"--"+ value);
							}
							break;
							
						case "CLOSE_BROWSER":
							driver.quit();
							extentTest.info( steps);
							System.out.println(steps +"--"+ value);
							break;
							
						case "ENTER_URL":
							if (value.isEmpty() || value.equals("NA")) {
								driver.get(prop.getProperty("url"));
								extentTest.info(steps+"--"+prop.getProperty("browser"));
								System.out.println(steps +"--"+ value);
							} else {
								driver.get(value);
								extentTest.info( steps +"--"+ value);
								System.out.println(steps +"--"+ value);
							}
							break;
							
						case "WAIT":
							Thread.sleep(10000);
							extentTest.info(steps+"--10 secs");
							System.out.println(steps +"--"+ value);
							break;
							
						case "IMPLICITLYWAIT":
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
							extentTest.info( steps+"--Implicit Wait");
							System.out.println(steps +"--"+ value);
							break;
							
						default:;
						}
						switch(locatorType)
						{
						case"id":
							element = driver.findElement(By.id(locatorValue));
							if(action.equalsIgnoreCase("ENTER")) {
								element.clear();
								element.sendKeys(value);
								extentTest.info(steps+"--"+value);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("CLICK")) {
								element.click();
								extentTest.info(steps+"--"+value);
								System.out.println(steps+"--"+ value);
							}
							else if(action.equalsIgnoreCase("ISDISPLAYED")) {
								Boolean bol=element.isDisplayed();
								
								if(bol) {
								Assert.assertEquals(bol, value);
								extentTest.info(steps+"--"+value);
								}else {
									extentTest.fail(steps+"--"+bol);
								}
			
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("VERIFYEXPECTEDMESSAGE")) {
								String elemenText = element.getText();
								Assert.assertEquals(value, elemenText);
								//Assert.assertEquals(value, elemenText);
								extentTest.info(steps+"--"+value);
								System.out.println("text from element : " +elemenText);
								System.out.println(steps+"--"+ value);
							} else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
								WebDriverWait wait= new WebDriverWait(driver, 20);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
								extentTest.info(steps+"--"+value);
								System.out.println(steps +"--"+ value);
							}
							
							
							locatorType = null;
							break;
							
						case"xpath":
							element = driver.findElement(By.xpath(locatorValue));
							//String ExplicitWaiElement = "By.xpath("+locatorValue+")";
							if(action.equalsIgnoreCase("ENTER")) {
								element.clear();
								element.sendKeys(value);
								extentTest.info(steps+"--"+value);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("CLICK")) {
								element.click();
								extentTest.info(steps+"--"+value);
								System.out.println(steps+"--"+ value);
							}
							else if(action.equalsIgnoreCase("isDisplayed")) {
								element.isDisplayed();
								extentTest.info(steps+"--"+value);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("VERIFYEXPECTEDMESSAGE")) {
								String elemenText = element.getText();
								Assert.assertEquals(value, elemenText);
								System.out.println("text from element : " +elemenText);
								extentTest.info(steps+"--"+elemenText);
								System.out.println(steps+"--"+ value);
							}else if (action.equalsIgnoreCase("SELECTDROPDOWNVISIBLETEXT")) {
								Select selectDropDFwon=new Select(element);
								selectDropDFwon.deselectByVisibleText(value);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("SELECTDROPDWONBYVALUE")) {
								Select selectDropDowon=new Select(element);
								selectDropDowon.deselectByValue(value);
								extentTest.info(steps);
								System.out.println(steps+"--"+ value);
							} else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
								WebDriverWait wait= new WebDriverWait(driver, 20);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
								System.out.println(steps +"--"+ value);
							}else if(action.equalsIgnoreCase("VERIFYTEXT")) {
								String actualText=element.getText();
								if(value.contentEquals(actualText)) {
									extentTest.info(steps+"--"+"Actual:"+actualText+"--Expected:"+value);
								}else {
									extentTest.fail(steps+"--"+"Actual:"+actualText+"--Expected:"+value);
								}
								System.out.println(steps+"--"+ value);
							}
							
							locatorType = null;
							break;
							
						case "className":
							element = driver.findElement(By.className(locatorValue));
							if (action.equalsIgnoreCase("sendKeys")) {
								element.clear();
								element.sendKeys(value);
							} else if(action.equalsIgnoreCase("click")) {
								element.click();
							} else if(action.equalsIgnoreCase("isDisplayed")) {
								element.isDisplayed();
							}else if(action.equalsIgnoreCase("getText")) {
								String elementText = element.getText();
								Assert.assertEquals(value, elementText);
								System.out.println("text from element : " +elementText);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
								WebDriverWait wait= new WebDriverWait(driver, 20);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
								System.out.println(steps +"--"+ value);
							}
							
							locatorType = null;
							break;
							
						case "cssSelector":
							element = driver.findElement(By.cssSelector(locatorValue));
							if (action.equalsIgnoreCase("sendKeys")) {
								element.clear();
								element.sendKeys(value);
							} else if(action.equalsIgnoreCase("click")) {
								element.click();
							} else if(action.equalsIgnoreCase("isDisplayed")) {
								element.isDisplayed();
							}else if(action.equalsIgnoreCase("getText")) {
								String elementText = element.getText();
								Assert.assertEquals(value, elementText);
								System.out.println("text from element : " +elementText);
								System.out.println(steps+"--"+ value);
							}else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
								WebDriverWait wait= new WebDriverWait(driver, 20);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
								System.out.println(steps +"--"+ value);
							}
							
							locatorType = null;
							break;
							
						case "name":
							element = driver.findElement(By.name(locatorValue));
							if (action.equalsIgnoreCase("sendKeys")) {
								element.clear();
								element.sendKeys(value);
							} else if(action.equalsIgnoreCase("click")) {
								element.click();
							} else if(action.equalsIgnoreCase("isDisplayed")) {
								element.isDisplayed();
							}else if(action.equalsIgnoreCase("getText")) {
								String elementText = element.getText();
								Assert.assertEquals(value, elementText);
								System.out.println("text from element : " +elementText);
								System.out.println(steps+"--"+ value);
							} else if(action.equalsIgnoreCase("EXPLICITWAIT")) {
								WebDriverWait wait= new WebDriverWait(driver, 20);
								wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
								System.out.println(steps +"--"+ value);
							}
							
							locatorType = null;
							break;
							
						case "linkText":
							element = driver.findElement(By.linkText(locatorValue));
							element.click();
							System.out.println(steps+"--"+ value);
							
							locatorType = null;
							break;
							
						case "partialLinkText":
							element = driver.findElement(By.partialLinkText(locatorValue));
							element.click();
							System.out.println(steps+"--"+ value);
							
							locatorType = null;
							break;
							
							
							
						}
					}
					
					catch(Exception e) {
						extentTest.fail(steps+"--"+e);
					}
				
				}
					}
		extentreport.flush();
	}
		}
		}
	}

