package com.example.test;

import com.example.finance.YahooFinance;
import com.example.model.StockInfo;

public class TestYahoo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StockInfo si = YahooFinance.getStockInfo("GOOG");
		System.out.println(si);
	}

}