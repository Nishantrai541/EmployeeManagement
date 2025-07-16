package com.taskManagement.taskManagement.controller.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskManagement/test")
public class Test {
	
	@GetMapping
	public String test() {
		
		return "hello friend";
	}
	
	@PostMapping
	public String test(@RequestParam(required=true) Long id) {
		
		return "hello friend"+id;
	}

}
