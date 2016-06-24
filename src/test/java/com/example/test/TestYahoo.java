/**
 * Unit Test to test Parse Data Yahoo API
 */
package com.example.test;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.Test;

import com.example.finance.YahooFinance;
import com.example.model.StockHistory;
import com.example.model.StockInfo;

import junit.framework.TestCase;

/**
 * @author Yi
 *
 */
public class TestYahoo extends TestCase {
	
	@Test
	public void testCurrentStock(){
		StockInfo si = YahooFinance.getStockInfo("GOOG");
		assertNotNull(si.getName());
		assertNotNull(si.getChange());
		assertNotNull(si.getPrice());
		assertNotNull(si.getPercentChange());
		assertEquals("Alphabet Inc.", si.getName());
	}
	
	
	@Test
	public void testCurrentStock2(){
		StockInfo si = YahooFinance.getStockInfo("INVALIDSYMBOL");
		assertEquals("", si.getName());
	}
	
	
	@Test
	public void testHistoryStock() throws ParseException{
		StockHistory sh = YahooFinance.getStockHistory("GOOG", "03/04/2016", "04/05/2016");
		assertTrue(sh.getRecords().size()>1);
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testHistoryStock2() throws ParseException{
		StockHistory sh = YahooFinance.getStockHistory("FAKESYMBOL", "03/04/2016", "04/05/2016");
	}
	
	
}
