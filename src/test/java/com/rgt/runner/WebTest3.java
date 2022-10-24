package com.rgt.runner;

import java.io.IOException;

import org.testng.annotations.Test;

import com.rgt.engine.TestDriver;
import com.rgt.engine.TestDriver2;
import com.rgt.engine.TestDriver3;

public class WebTest3 {
	
	@Test
	public void test() throws IOException, InterruptedException {
		TestDriver3 td= new TestDriver3();
		td.startExecution();
		
	}

}
