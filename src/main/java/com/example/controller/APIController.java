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

import com.example.model.Stock;
import com.example.model.StockHistory;
import com.example.model.StockInfo;
import com.example.service.StockService;


@Controller
@SessionAttributes
public class APIController {
	@Autowired
	private StockService stockService;
	
	//add stock   need to change to post
	@RequestMapping(value="/stock/{symbol}", method=RequestMethod.GET)
	@ResponseBody
	public void addStock(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		this.stockService.insert(symbol);
	}
	
	//haven't check yet
	@RequestMapping(value="/stock/delete/{symbol}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteStock(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		this.stockService.delete(symbol);
	}
	
	
	@RequestMapping(value="/stock", method=RequestMethod.GET)
	@ResponseBody
	public List<StockInfo> listStock(){
		return this.stockService.listAllStock();
	}
	
	
	@RequestMapping(value="/stockdetail/{symbol}", method=RequestMethod.GET)
	@ResponseBody
	public StockInfo getStockDetail(@PathVariable String symbol){
		symbol = symbol.toUpperCase();
		return this.stockService.getStockDetail(symbol);
	}
	
	
	@RequestMapping(value="/stock/history/{symbol}", method=RequestMethod.GET)
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
