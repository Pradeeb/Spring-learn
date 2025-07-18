package com.transacationlearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transacationlearn.model.Student;
import com.transacationlearn.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class Strudents {
	
	@Autowired private StudentService studentService;
	
	@PostMapping(path = "/add")
	public List<Student> addSrudent(@RequestBody List<Student> students){
		System.err.println("add student controller run");
		return studentService.addStudents(students);
	}

}
