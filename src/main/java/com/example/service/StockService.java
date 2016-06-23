package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StockDao;
import com.example.model.Stock;
import com.example.model.StockInfo;

@Service
public class StockService {
	@Autowired
	private StockDao sd;
	
	public StockInfo process(Stock stock){
		sd.save(stock);
		StockInfo stockInfo = new StockInfo();
		stockInfo.setMessage("Hello" + stock.getName() + ", Welcome!");
		stockInfo.setStocks(sd.queryAll());
		return stockInfo;
	}
}
