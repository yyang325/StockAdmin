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



/**
 * This is the Service Layer to provide different services for controller.
 * @author Yi Yang
 * @since  06/23/2016
 */
@Service
public class StockService {
	@Autowired
	private StockDao stockDao;
	
	/**
	 * Method to get stock's current detail information provided by Yahoo Finance API
	 * @param symbol: stock's symbol
	 * @return StockInfo -- stock's current price, change etc.
	 * @author Yi Yang
	 */
	public StockInfo getStockDetail(String symbol){
		return YahooFinance.getStockInfo(symbol);
	}
	
	
	
	/**
	 * Method to get stock's History detail information provided by Yahoo Finance API
	 * @param symbol: stock's symbol, start: start date, end: end date
	 * @return StockHistory -- stock's History high price, low price, close price etc.
	 * @author Yi Yang
	 */
	public StockHistory getStockHistory(String symbol, String start, String end) throws ParseException{
		return YahooFinance.getStockHistory(symbol, start, end);
	}
	
	
	
	/**
	 * Method to search stock's symbol detail information provided by Yahoo Finance API
	 * @param symbol: stock's symbol
	 * @return Stock
	 * @author Yi Yang
	 */
	public Stock searchSymbol(String symbol){
		StockInfo stockInfo = YahooFinance.getStockInfo(symbol);
		Stock stock = new Stock();
		stock.setName(stockInfo.getName());
		stock.setSymbol(stockInfo.getSymbol());
		return stock;
	}
	
	
	/**
	 * Method to Insert a stock into DB
	 * 		If stock symbol is null or invalid, return directly
	 * 		If stock has already in DB, return directly
	 * @param symbol: stock's symbol
	 * @author Yi Yang
	 */
	public void insert(String symbol){
		Stock s = searchSymbol(symbol);
		if(s == null || s.getName() == null || s.getName().length() < 1) return;
		Stock old = this.stockDao.findBySymbol(symbol);
		if(old == null || old.getName() == null || old.getName().length() < 1){
			Stock stock = searchSymbol(symbol);
			this.stockDao.save(stock);
		}
	}
	
	
	
	/**
	 * Method to Delete a stock from DB
	 * 		If stock symbol is null or invalid, return directly
	 * 		If stock is not in DB, return directly
	 * @param symbol: stock's symbol
	 * @author Yi Yang
	 */
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
	
	
	
	/**
	 * Method to List All Stocks from DB, and display their detail Information
	 * @author Yi Yang
	 */
	public List<StockInfo> listAllStock(){
		List<Stock> list = this.stockDao.queryAll();
		List<StockInfo> res = new ArrayList<StockInfo>();
		for(Stock stock: list){
			res.add(getStockDetail(stock.getSymbol()));
		}
		return res;
	}
	
	
}
