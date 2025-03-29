package com.spring.basic;

public class Student {
	
	private int age;
	private int role;
	private Writer write;
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	public int getRole() {
		return role;
	}
	
	public void setRole(int role) {
		this.role=role;
	}
	
	public Writer getWriter() {
		return write;
	}
	
	public void setWriter(Writer write) {
		this.write=write;
	}
	
	// when the class load constructor runn
//	Student(){
//		System.out.println("Student object is creadted");
//	}
	
	public void show() {
		System.out.println("This is stutdent");
	}
	
	public void write() {
		write.write();
	}

}
