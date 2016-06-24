package com.example.finance;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.StockHistory;
import com.example.model.StockInfo;


/**
 * Class to implement Yahoo Finance API Searching Stock Current and Historical Detail Information
 * @author Yi Yang
 *
 */
public class YahooFinance {
	
	/**
	 * Use Yahoo API: http://finance.yahoo.com/d/quotes.csv?s={symbol name}&f=snc1l1p2&e=.c
	 * to get coresponding stock data such as Name, Price, Change, Precent Change.
	 * @param symbol
	 * @return
	 */
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
			
			/* If Stock Symbol is not valid, return null value */
			int length = tokens.length;
			if (tokens.length <4) return null;
			if(!tokens[tokens.length-4].trim().equals("N/A")){
				for (int i= length-4; i>0; i--){
					name = tokens[i].trim() + name;
				}
				percentChange = tokens[tokens.length-1].trim();
				price = Double.parseDouble(tokens[tokens.length-2].trim());
				change = Double.parseDouble(tokens[tokens.length-3].trim());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		StockInfo stockinfo = new StockInfo();
		
		/* Set up properties to StockInfo*/
		stockinfo.setChange(change);
		stockinfo.setName(name);
		stockinfo.setPercentChange(percentChange);
		stockinfo.setPrice(price);
		stockinfo.setSymbol(symbol);
		
		return stockinfo;	
	}
	
	
	
	/**
	 * Use Yahoo API: http://ichart.yahoo.com/table.csv?s={symbol}&a={StartMonth}&b={StartDate}&c={StartYear}&d={EndMonth}&e={EndDate}&f={EndYear}&g=d&ignore=.csv
	 * @param symbol -- Stock Symbol 
	 * @param start -- start date in query
	 * @param end -- end date in query
	 * @return Stock History Class
	 * @throws ParseException
	 */
	public static StockHistory getStockHistory(String symbol, String start, String end) throws ParseException{
		List<String> records = null;
		String[] startInfo = start.split("/");
		String[] endInfo = end.split("/");
		String yahoo_quote = "http://ichart.yahoo.com/table.csv?s="+symbol+"&a="+startInfo[0]+"&b="+startInfo[1]+"&c="+startInfo[2]+"&d="+endInfo[0]+"&e="+endInfo[1]+"&f="+endInfo[2]+"&g=d&ignore=.csv";
		try {
			URL url = new URL(yahoo_quote);
			URLConnection urlconn = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
			String line = null;
			
			records = new ArrayList<String>();
			while ((line = in.readLine()) != null){
				records.add(line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		StockHistory sh = new StockHistory();
		sh.setSymbol(symbol);
		sh.setRecords(records);
		return sh;
	}
}
