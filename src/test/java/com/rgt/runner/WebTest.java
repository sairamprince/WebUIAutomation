package com.rgt.runner;

import java.io.IOException;

import org.testng.annotations.Test;

import com.rgt.engine.TestDriver;
import com.rgt.engine.TestDriver2;

public class WebTest {
	
	@Test
	public void test() throws IOException, InterruptedException {
		TestDriver td= new TestDriver();
		td.startExecution();
		
	}

}
