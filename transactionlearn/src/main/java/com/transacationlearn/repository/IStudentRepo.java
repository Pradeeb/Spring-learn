package com.transacationlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transacationlearn.model.Student;

@Repository
public interface IStudentRepo extends JpaRepository<Student, Long> {

}
