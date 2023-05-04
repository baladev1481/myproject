package com.java.training.microservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path = "/helloworldbean/{personName}")
	public HelloBean helooWorldBean(@PathVariable String personName) {
		return new HelloBean("Welcome :: " + personName);
	}
}
