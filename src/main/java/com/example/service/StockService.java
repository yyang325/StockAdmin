package com.example.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StockDao;
import com.example.finance.YahooFinance;
import com.example.model.Stock;
import com.example.model.StockHistory;
import com.example.model.StockInfo;

@Service
public class StockService {
	@Autowired
	private StockDao stockDao;
	
	public StockInfo getStockDetail(String symbol){
		return YahooFinance.getStockInfo(symbol);
	}
	
	public StockHistory getStockHistory(String symbol, String start, String end) throws ParseException{
		return YahooFinance.getStockHistory(symbol, start, end);
	}
	
	public Stock searchSymbol(String symbol){
		StockInfo stockInfo = YahooFinance.getStockInfo(symbol);
		Stock stock = new Stock();
		stock.setName(stockInfo.getName());
		stock.setSymbol(stockInfo.getSymbol());
		return stock;
	}
	
	public void insert(String symbol){
		Stock s = searchSymbol(symbol);
		if(s == null || s.getName() == null || s.getName().length() < 1) return;
		Stock old = this.stockDao.findBySymbol(symbol);
		if(old == null || old.getName() == null || old.getName().length() < 1){
			Stock stock = searchSymbol(symbol);
			this.stockDao.save(stock);
		}
	}
	
	public void delete(String symbol){
		Stock s = searchSymbol(symbol);
		if(s == null || s.getName() == null || s.getName().length() < 1) return;
		Stock old = this.stockDao.findBySymbol(symbol);
		if(old == null || old.getName() == null || old.getName().length() < 1){
			return;
		}
		Stock stock = this.stockDao.findBySymbol(symbol);
		this.stockDao.delete(stock);
	}
	
	public List<StockInfo> listAllStock(){
		List<Stock> list = this.stockDao.queryAll();
		List<StockInfo> res = new ArrayList<StockInfo>();
		for(Stock stock: list){
			res.add(getStockDetail(stock.getSymbol()));
		}
		return res;
	}
	
	
}
