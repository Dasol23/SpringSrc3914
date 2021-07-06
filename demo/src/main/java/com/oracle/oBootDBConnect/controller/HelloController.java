package com.oracle.oBootDBConnect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping 
	("Hello")
	public String hello(Model model) {
		System.out.println("HelloController Hello start...");
		model.addAttribute("data", "Server Parameter");
		return "hello";
	}
	@GetMapping("HelloGet")
	public String helloGet(Model model) {
		System.out.println("HelloController helloGet start");
		model.addAttribute("data", "Get Parameter");
		return "helloGet";
	}
}
