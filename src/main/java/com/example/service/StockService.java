package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StockDao;
import com.example.finance.YahooFinance;
import com.example.model.Stock;
import com.example.model.StockInfo;

@Service
public class StockService {
	@Autowired
	private StockDao stockDao;
	
	public StockInfo getStockDetail(String symbol){
		return YahooFinance.getStockInfo(symbol);
	}
	
	public Stock searchSymbol(String symbol){
		StockInfo stockInfo = YahooFinance.getStockInfo(symbol);
		Stock stock = new Stock();
		stock.setName(stockInfo.getName());
		stock.setSymbol(stockInfo.getSymbol());
		return stock;
	}
	
	public void insert(String symbol){
		Stock stock = searchSymbol(symbol);
		this.stockDao.save(stock);
	}
	
	public void delete(String symbol){
		Stock stock = this.stockDao.findBySymbol(symbol);
		this.stockDao.delete(stock);
	}
	
	public List<Stock> listAllStock(){
		return this.stockDao.queryAll();
	}
	
	
}
