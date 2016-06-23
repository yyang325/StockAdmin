package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes
public class ViewController {
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String homePage(){
		return "home";
	}
}
