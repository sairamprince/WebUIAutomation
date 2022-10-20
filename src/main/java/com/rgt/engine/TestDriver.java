package com.rgt.engine;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.rgt.base.Base;
import com.rgt.utils.ExcelUtils;

public class TestDriver 
{
	public WebDriver driver;
	public Properties prop;
	public ExcelUtils excel;
	public Base base;
	public WebElement element;
	
	public final String SCENARIO_SHEET_PATH = System.getProperty("user.dir")+"/resources/datafiles/TC_Master.xlsx";
	
	
	public void startExecution() {
		
		excel = new ExcelUtils(SCENARIO_SHEET_PATH);
		int count =excel.getTestdata().size();
		
		for (int i = 0; i < count; i++) {
			try {
				String locatorType = excel.getTestdata().get(i).getLocatorType();
				String locatorValue = excel.getTestdata().get(i).getLocatorValue();
				String action = excel.getTestdata().get(i).getAction();
				String value = excel.getTestdata().get(i).getInputValue();
				String steps = excel.getTestdata().get(i).getTestSteps();
				
				switch (action) {
				case "OPEN_BROWSER":
					base = new Base();
					prop =base.init_properties();
					if (value.isEmpty() || value.equals("NA")) {
						driver = base.init_driver(prop.getProperty("browser"));
						System.out.println(steps);
					} else {
						driver = base.init_driver(value);
						System.out.println(steps +"--"+ value);
					}
					break;
					
				case "CLOSE_BROWSER":
					driver.quit();
					System.out.println(steps +"--"+ value);
					break;
					
				case "ENTER_URL":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));
						System.out.println(steps +"--"+ value);
					} else {
						driver.get(value);
						System.out.println(steps +"--"+ value);
					}
					break;
					
				case "quit":
					driver.quit();
					System.out.println(steps +"--"+ value);
					break;
					
				case "WAIT":
					Thread.sleep(10000);
					System.out.println(steps +"--"+ value);
					break;
					
				case "implicitlyWait":
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					System.out.println(steps +"--"+ value);
					break;
					
				case "ExplicitWait":
					WebDriverWait wait= new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					System.out.println(steps +"--"+ value);
					wait.until(ExpectedConditions.titleContains("Enter"));
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
						System.out.println(steps+"--"+ value);
					}else if(action.equalsIgnoreCase("CLICK")) {
						element.click();
						System.out.println(steps+"--"+ value);
					}
					else if(action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
						System.out.println(steps+"--"+ value);
					}else if(action.equalsIgnoreCase("VERIFYEXPECTEDMESSAGE")) {
						String elemenText = element.getText();
						Assert.assertEquals(value, elemenText);
						//Assert.assertEquals(value, elemenText);
						System.out.println("text from element : " +elemenText);
						System.out.println(steps+"--"+ value);
					}
					
					
					locatorType = null;
					break;
					
				case"xpath":
					element = driver.findElement(By.xpath(locatorValue));
					if(action.equalsIgnoreCase("ENTER")) {
						element.clear();
						element.sendKeys(value);
						System.out.println(steps+"--"+ value);
					}else if(action.equalsIgnoreCase("CLICK")) {
						element.click();
						System.out.println(steps+"--"+ value);
					}
					else if(action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
						System.out.println(steps+"--"+ value);
					}else if(action.equalsIgnoreCase("VERIFYEXPECTEDMESSAGE")) {
						String elemenText = element.getText();
						Assert.assertEquals(value, elemenText);
						System.out.println("text from element : " +elemenText);
						System.out.println(steps+"--"+ value);
					}else if (action.equalsIgnoreCase("SELECTDROPDOWNVISIBLETEXT")) {
						Select selectDropDFwon=new Select(element);
						selectDropDFwon.deselectByVisibleText(value);
						System.out.println(steps+"--"+ value);
					}else if(action.equalsIgnoreCase("SELECTDROPDWONBYVALUE")) {
						Select selectDropDFwon=new Select(element);
						selectDropDFwon.deselectByValue(value);
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
				
			}
		
	
			}
			
		}
	}

