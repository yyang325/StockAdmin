package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


/**
 * This is the controller class to implement Views Page
 * @author Yi Yang
 * @since  06/23/2016
 */
@Controller
@SessionAttributes
public class ViewController {
	
	/*
	 * Stock Price Monitor's Main Page
	 * In this page, user can Add, Delete, Get stock's current price and history performance.
	 * 
	 * */
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String mainPage(){
		return "main";
	}
}
