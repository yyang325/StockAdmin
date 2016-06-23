package com.example.dao;

import java.util.List;

import com.example.model.Stock;

public interface StockDao {
	public Stock findByName(String name);
	public Stock findBySymbol(String symbol);
	public void save(Stock user);
	public void update(Stock user);
	public void delete(Stock user);
	public List<Stock> queryAll();
}
