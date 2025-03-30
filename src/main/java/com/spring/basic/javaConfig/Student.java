package com.spring.basic.javaConfig;

public class Student {
	
	Student(){
		System.out.println("Student Object created");
	}
	
	private int age;
	private Writer write;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Writer getWrite() {
		return write;
	}

	public void setWrite(Writer write) {
		this.write = write;
	}
	

	public void show() {
		System.out.println("sudent how method");
	}
	
	public void exam() {
		write.write();
	}

}
