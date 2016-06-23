package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.model.Stock;
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
		this.stockService.insert(symbol);
	}
	
	//haven't check yet
	@RequestMapping(value="/stock/delete/{symbol}", method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteStock(@PathVariable String symbol){
		this.stockService.delete(symbol);
	}
	
	
	@RequestMapping(value="/stock", method=RequestMethod.GET)
	@ResponseBody
	public List<Stock> listStock(){
		return this.stockService.listAllStock();
	}
	
	
	@RequestMapping(value="/stockdetail/{symbol}", method=RequestMethod.GET)
	@ResponseBody
	public StockInfo getStockDetail(@PathVariable String symbol){
		return this.stockService.getStockDetail(symbol);
	}
}
