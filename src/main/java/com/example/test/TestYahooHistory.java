package com.example.test;

import java.text.ParseException;

import com.example.finance.YahooFinance;

public class TestYahooHistory {
	public static void main(String[] args) throws ParseException{
		YahooFinance.getStockHistory("FB", "02/20/2016", "05/20/2016");
	}
}
