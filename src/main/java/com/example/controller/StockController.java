package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Stock;
import com.example.model.StockInfo;
import com.example.service.StockService;

@Controller
@SessionAttributes
public class StockController {
	@Autowired
	private StockService ss;
	
	@RequestMapping(value="/stock/{symbol}/{name}", method=RequestMethod.GET)
	@ResponseBody
	public StockInfo process(@PathVariable String symbol, @PathVariable String name) {
		Stock stock = new Stock();
		stock.setName(name);
		stock.setSymbol(symbol);
		StockInfo stockInfo = ss.process(stock);
		return stockInfo;
	}
	
	
}
