package edu.umb.cs681.hw1;

public class StockEvent {
	private String ticker;
	private float quote;
	
	public StockEvent(String ticker, float quote){
		super();
		this.ticker = ticker;
		this.quote = quote;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public float getQuote() {
		return quote;
	}
	
	public void setQuote(float quote) {
		this.quote = quote;
	}
}
