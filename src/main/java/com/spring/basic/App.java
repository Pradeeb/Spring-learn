package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		/* Java object creation method */
		Student valan=new Student();
		 // valan.show();

		
		//XML use object create spring concept
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		
		//if configuration XML have one object its working
		Teacher teacher=context.getBean(Teacher.class);
		
		//if Configuration XML have Multiple  object in same Class get Object name use
		Student student=(Student) context.getBean("st1");

		student.setAge(25);
		
		System.out.println(student.getAge()+"  "+student.getRole());
		student.write();

	}

}
