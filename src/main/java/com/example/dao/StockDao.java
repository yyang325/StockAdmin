package com.example.dao;

import java.util.List;

import com.example.model.Stock;

public interface StockDao {
	public Stock findByName(String name);
	public Stock findBySymbol(String symbol);
	public void save(Stock stock);
	public void update(Stock stock);
	public void delete(Stock stock);
	public List<Stock> queryAll();
}
