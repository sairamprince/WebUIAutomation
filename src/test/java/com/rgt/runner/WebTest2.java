package com.rgt.runner;

import java.io.IOException;

import org.testng.annotations.Test;

import com.rgt.engine.TestDriver;
import com.rgt.engine.TestDriver2;

public class WebTest2 {
	
	@Test
	public void test() throws IOException, InterruptedException {
		TestDriver2 td= new TestDriver2();
		td.startExecution();
		
	}

}
