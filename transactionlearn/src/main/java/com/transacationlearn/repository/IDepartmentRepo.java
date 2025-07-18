package com.transacationlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transacationlearn.model.Department;

@Repository
public interface IDepartmentRepo extends JpaRepository<Department, Short> {

}
