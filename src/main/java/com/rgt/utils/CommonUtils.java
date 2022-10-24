package com.rgt.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtils {
	
		
	public WebElement getLocators(WebDriver driver,String locatorType, String locatorValue) {
		WebElement element = null;
		switch(locatorType) {
		
		case "id": element = driver.findElement(By.id(locatorValue));
		break;
		
		case "xpath": element = driver.findElement(By.xpath(locatorValue));
		break;
		
		case "linkText": element = driver.findElement(By.linkText(locatorValue));
		break;
		
		case "partialLinkText": element = driver.findElement(By.partialLinkText(locatorValue));
		break;
		
		case "name": element = driver.findElement(By.name(locatorValue));
		break;
		
		case "ClassName": element = driver.findElement(By.className(locatorValue));
		break;
		
		case "tagName": element = driver.findElement(By.tagName(locatorValue));
		break;
		
		case "cssSelector": element = driver.findElement(By.cssSelector(locatorValue));
		break;
			
		}
		
		return element;
	}
	
	public void explicitWait(WebDriver driver,String locatorType, String locatorValue) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		switch(locatorType) {
		case "id":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			break;
		case "xpath":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			break;
		case "linkText":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
			break;
		case "partialLinkText":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
			break;
		case "name":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
			break;
		case "className":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
			break;
		case "cssSelector":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
			break;
		case "tagName":
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
			break;
			
			
		}
			
	}

}
