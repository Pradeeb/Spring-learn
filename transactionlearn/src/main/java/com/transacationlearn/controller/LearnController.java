package com.transacationlearn.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/learn")  // Add this line!
public class LearnController {

	@GetMapping(path = "/test")
	public String sendMessage() {
		return "Success";
	}
}
