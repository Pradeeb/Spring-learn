package com.transacationlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transacationlearn.model.Department;
import com.transacationlearn.repository.IDepartmentRepo;

@Service
public class DepartmentService {

	@Autowired private IDepartmentRepo departmentRepo;
	
	public List<Department> addDipartment(List<Department> departments){
		return departmentRepo.saveAll(departments);
	}
	
}
