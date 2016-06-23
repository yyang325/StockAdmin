package com.example.model;

public class StockInfo {
	private String name;
	private String symbol;
	private double price;
	private double change;
	private String percentChange;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public String getPercentChange() {
		return percentChange;
	}
	public void setPercentChange(String percentChange) {
		this.percentChange = percentChange;
	}
	
	public String toString(){
		return "Name: " + this.name + "; Symbol:" + this.symbol + "; Price:" + this.price + "; Change:" + this.change + "; Percent Change: " + this.percentChange;
	}
}
