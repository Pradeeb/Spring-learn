package com.transacationlearn.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "student")
@Data
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="name",nullable =false,length=30,insertable = true,updatable = true)
	private String name;
	
	@Column(name="mobile_no",nullable =false,length=10,insertable = true,updatable = true)
	private String mobileNo;
	
	@Column(name="email",nullable =false,length=30,insertable = true,updatable = true,unique = true)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	@JsonBackReference
	private Department department;
}
