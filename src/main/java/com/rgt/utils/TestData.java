package com.rgt.utils;

public class TestData 
{
	private String TC_ID;
	private String InputValue;
	private String ToBeExecuted;
	private String Steps;
	private String TestCase;
	private String LocatorType;
	private String LocatorValue;
	private String Action;
	private String TestSteps;
	
	public String getTC_ID() {
		return TC_ID;
	}
	public void setTC_ID(String tc_ID){
		TC_ID = tc_ID;
	}
	
	public String getSteps() {
		return Steps;
	}
	public void setSteps(String steps) {
		Steps = steps;
	}
	
	public String getToBeExecuted() {
		return ToBeExecuted;
	}
	 public void setToBeExecuted(String toBeExecuted) {
		 ToBeExecuted = toBeExecuted;
	 }
	 
	 public String getTestCse() {
		 return TestCase;
	 }
	 
	 public void setTestCase(String testCase) {
		 TestCase = testCase;
	 }
	 
	 public String getLocatorType() {
		 return LocatorType;
	 }
	 
	 public void setLocatorType(String locatorType) {
		 LocatorType = locatorType;
	 }
	 
	 public String getLocatorValue() {
		 return LocatorValue;
	 }
	 
	 public void setLocatorValue(String locatorValue) {
		 LocatorValue = locatorValue;
	 }
	 
	 public String getAction() {
		 return Action;
	 }
	 
	 public void setAction(String action) {
		 Action = action;
	 }
	 
	 public String getInputValue() {
		 return InputValue;
	 }
	 
	 public void setInputValue(String inputValue) {
		 InputValue = inputValue;
	 }
	 
	 public String getTestSteps() {
		 return TestSteps;
	 }
	 
	 public void setTestSteps(String testSteps) {
		 TestSteps = testSteps;
	 }
	 
	/* public String toString() {
		 return "Testdata [TC_ID="+TC_ID+", ToBeExecuted="+ToBeExecuted+", Steps="+Steps+",TestCase="+TestCase+", LocatorType="+LocatorType+",LocatorValue"+LocatorValue+",InputValue="+InputValue+",Action="+Action+",TestSteps="+TestSteps+"]";
		 
	 }*/
	 
	

}
