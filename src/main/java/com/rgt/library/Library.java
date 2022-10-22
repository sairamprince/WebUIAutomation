package com.rgt.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Library {
	WebDriver driver;
	WebElement element;
	Boolean bool;
	 String txt ;
	public void enterValue(WebDriver driver,String locatorType,String locatorValue, String inputValue) {
		switch(locatorType) {
			case "id":
				driver.findElement(By.id(locatorValue)).clear();
				driver.findElement(By.id(locatorValue)).sendKeys(inputValue);
				break;
			case "xpath":
				driver.findElement(By.xpath(locatorValue)).clear();
				driver.findElement(By.xpath(locatorValue)).sendKeys(inputValue);
				break;
			default:System.out.println("Invalid Locator");
			}
			
	}
	
	public void click(WebDriver driver,String locatorType,String locatorValue, String inputValue) {
		switch(locatorType) {
		case "id":
			driver.findElement(By.id(locatorValue)).click();
			break;
		case "xpath":
			driver.findElement(By.xpath(locatorValue)).click();
			break;
		default:System.out.println("Invalid Locator");
		}
		
		
	}
   public Boolean isDisplayed(WebDriver driver,String locatorType,String locatorValue, String inputValue) {
	   
	   switch(locatorType) {
	   
		case "id":
			bool=driver.findElement(By.id(locatorValue)).isDisplayed();
			break;
		case "xpath":
			bool=driver.findElement(By.xpath(locatorValue)).isDisplayed();
			break;
		default:System.out.println("Invalid Locator");
		}
		return bool;
		
	}
   public void explicitWait(WebDriver driver,String locatorType,String locatorValue, String inputValue) {
	   WebDriverWait wait= new WebDriverWait(driver, 20);
	   switch(locatorType) {
		case "id":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			break;
		case "xpath":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			break;
		default:System.out.println("Invalid Locator");
		}
		
	}
   public String verifyText(WebDriver driver,String locatorType,String locatorValue, String inputValue) {
	  
	   switch(locatorType) {
		case "id":
			txt=driver.findElement(By.id(locatorValue)).getText();
			break;
		case "xpath":
			txt=driver.findElement(By.xpath(locatorValue)).getText();
			break;
		default:System.out.println("Invalid Locator");
		}
		
	   return txt;
	}


}
