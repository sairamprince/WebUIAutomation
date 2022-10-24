package com.rgt.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

}
