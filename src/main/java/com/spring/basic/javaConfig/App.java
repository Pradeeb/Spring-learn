package com.spring.basic.javaConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext context=new AnnotationConfigApplicationContext(MyConfiguration.class);
		
		Student s1=context.getBean(Student.class);
	
        System.out.println(s1.getWrite());

	}

}
