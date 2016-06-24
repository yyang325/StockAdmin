package com.example.model;

import java.util.List;

public class StockHistory {
	private String symbol;
	private List<String> records;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<String> getRecords() {
		return records;
	}
	public void setRecords(List<String> records) {
		this.records = records;
	}
	
}
