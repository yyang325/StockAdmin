package com.example.test;

import com.example.finance.YahooFinance;
import com.example.model.StockInfo;

public class TestYahoo {

	public static void main(String[] args) {
		StockInfo si = YahooFinance.getStockInfo("goog");
		System.out.println(si);
	}

}
