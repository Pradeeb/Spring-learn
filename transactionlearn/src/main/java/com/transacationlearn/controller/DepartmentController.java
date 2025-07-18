package com.transacationlearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transacationlearn.model.Department;
import com.transacationlearn.service.DepartmentService;

@RestController
@RequestMapping(path = "/api/department")
public class DepartmentController {
	
	@Autowired private DepartmentService departmentService;
	
	@PostMapping(path = "/add")
	public List<Department> addSrudent(@RequestBody List<Department> department){
		System.err.println("add Department controller run");
		return departmentService.addDipartment(department);
	}

}
