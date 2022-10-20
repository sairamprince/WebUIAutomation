package com.rgt.runner;

import org.testng.annotations.Test;

import com.rgt.engine.TestDriver;

public class WebTest {
	
	@Test
	public void test() {
		TestDriver td= new TestDriver();
		td.startExecution();
		
	}

}
