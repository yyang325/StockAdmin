package com.example.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.StockHistory;
import com.example.model.StockInfo;
import com.example.service.StockService;


/**
 * This is the controller class to implement REST API
 * @author Yi Yang
 * @since  06/23/2016
 */
@Controller
@SessionAttributes
public class APIController {
	@Autowired
	private StockService stockService;
	
	/*
	 * REST API
	 * Method: POST
	 * Value: /api/stock/{symbolName}
	 * 
	 * Function: Add Stock to DB
	 * 
	 * */
	@RequestMapping(value="/api/stock/{symbol}", method=RequestMethod.POST)
	@ResponseBody
	public void addStock(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		this.stockService.insert(symbol);
	}
	
	/*
	 * REST API
	 * Method: DELETE
	 * Value: /api/stock/delete/{symbolName}
	 * 
	 * Function: Delete Stock From DB
	 * 
	 * */
	@RequestMapping(value="/api/stock/delete/{symbol}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteStock(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		this.stockService.delete(symbol);
	}
	
	
	/*
	 * REST API
	 * Method: GET
	 * Value: /api/stock
	 * 
	 * Function: Get the List of Stock From DB
	 * 
	 * */
	@RequestMapping(value="/api/stock", method=RequestMethod.GET)
	@ResponseBody
	public List<StockInfo> listStock(){
		return this.stockService.listAllStock();
	}
	
	
	
	/*
	 * REST API
	 * Method: GET
	 * Value: /api/stock/{symbolName}
	 * 
	 * Function: Get Specific Stock's Current Detail Information
	 * 
	 * */
	@RequestMapping(value="/api/stockdetail/{symbol}", method=RequestMethod.GET)
	@ResponseBody
	public StockInfo getStockDetail(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		return this.stockService.getStockDetail(symbol);
	}
	
	
	
	
	/*
	 * REST API
	 * Method: GET
	 * Value: /api/stock/history/{symbolName}
	 * 
	 * Function: Get Specific Stock's History Detail Information
	 * 
	 * */
	@RequestMapping(value="/api/stock/history/{symbol}", method=RequestMethod.GET)
	@ResponseBody
	public StockHistory getStockHistory(@PathVariable String symbol) throws ParseException{
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String end = dateFormat.format(date);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		Date result = cal.getTime();
		String start = dateFormat.format(result);
		symbol = symbol.toUpperCase();
		return this.stockService.getStockHistory(symbol, start, end);
	}
}
