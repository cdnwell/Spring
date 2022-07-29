package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.MemberService;

@Controller
public class MainController {
	private MemberService service;
	
	public MainController(MemberService service) {
		super();
		this.service = service;
	}

	@RequestMapping("/")
	public String main(Model model) {
		
		return "main";
	}
	
}
