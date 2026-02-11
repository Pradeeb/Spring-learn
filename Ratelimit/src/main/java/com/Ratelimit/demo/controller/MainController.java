package com.Ratelimit.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/api/v1")
public class MainController {
	
	Map<Integer,String> student=new HashMap<>();
	
	@GetMapping(path="/get")
	public ResponseEntity<?> getDetails(){
		student.put(1,"valan");
		student.put(2,"pradeeb");
		
	return new ResponseEntity<>(student,HttpStatus.OK);
	}

}
