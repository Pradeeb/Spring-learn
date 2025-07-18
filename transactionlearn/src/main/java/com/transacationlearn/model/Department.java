package com.transacationlearn.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="department")
@Data
public class Department {
  
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private short id;
	
	@Column(name="department",nullable =false,length=50,insertable = true,updatable = true,unique = true)
	private String department;
	
	@OneToMany(mappedBy ="department",cascade = CascadeType.PERSIST,orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<Student> students;
	
}