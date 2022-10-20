package com.rgt.base;
import com.rgt.base.Base;

public class Demo {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		Base b=new Base();
		b.init_properties();
		System.out.println(b.init_properties());
	}

}
