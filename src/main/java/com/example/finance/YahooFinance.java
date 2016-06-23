package com.example.finance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.example.model.StockInfo;

public class YahooFinance {
	public static StockInfo getStockInfo(String symbol){
		String yahoo_quote = "http://finance.yahoo.com/d/quotes.csv?s=" +symbol + "&f=snc1l1p2&e=.c";
		String percentChange = null;
		String name = "";
		double price = 0;
		double change = 0;
		try {
			URL url = new URL(yahoo_quote);
			URLConnection urlconn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String content = in.readLine();
			
			content = content.replace((char)34, (char)32);//' ' replace '"'
			String[] tokens = content.split(",");
			
			//print tokens
//			for(String s: tokens){
//				System.out.println(s);
//			}
			
			int length = tokens.length;
			if (tokens.length <4) return null;
			if(!tokens[tokens.length-4].trim().equals("N/A")){
				for (int i= length-4; i>0; i--){
					name = tokens[i].trim() + name;
				}
			percentChange = tokens[tokens.length-1].trim();
			price = Double.parseDouble(tokens[tokens.length-2].trim());
			change = Double.parseDouble(tokens[tokens.length-3].trim());
			/*name =  tokens[tokens.length-4].trim();*/
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		StockInfo stockinfo = new StockInfo();
			
		stockinfo.setChange(change);
		stockinfo.setName(name);
		stockinfo.setPercentChange(percentChange);
		stockinfo.setPrice(price);
		stockinfo.setSymbol(symbol);
		
		return stockinfo;	
	}
	
}
