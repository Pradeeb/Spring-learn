package com.transacationlearn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transacationlearn.model.Student;
import com.transacationlearn.repository.IStudentRepo;

@Service
public class StudentService {
	
	@Autowired private IStudentRepo studentrepo;
	
	public List<Student> addStudents(List<Student> students){
		return studentrepo.saveAll(students);
	}

}
