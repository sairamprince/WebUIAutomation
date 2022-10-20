package com.rgt.library;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Library {
	WebDriver driver;
	WebElement element;
	public void enterValue(String locatorType,String locatorValue, String inputValue) {
		switch(locatorType) {
			case "id":
				driver.findElement(By.id(locatorValue)).clear();
				driver.findElement(By.id(locatorValue)).sendKeys(inputValue);
				break;
			case "xpath":
				driver.findElement(By.xpath(locatorValue)).clear();
				driver.findElement(By.xpath(locatorValue)).sendKeys(inputValue);
				break;
			}
			
	}
	
	public void click() {
		
	}

}
