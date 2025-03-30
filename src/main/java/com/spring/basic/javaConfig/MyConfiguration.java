package com.spring.basic.javaConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfiguration {

	@Bean
	public Student student(Writer write) {
		Student s1=new Student();
		s1.setAge(25);
		s1.setWrite(write);
		return new Student();
	}
	

	@Bean
	public Pen pen() {
		return new Pen();
		
	}
}
