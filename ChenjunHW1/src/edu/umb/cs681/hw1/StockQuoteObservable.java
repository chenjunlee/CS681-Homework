package edu.umb.cs681.hw1;

import java.util.HashMap;

public class StockQuoteObservable extends Observable {
	HashMap<String, Float> stocks;
	
	public StockQuoteObservable() {
		super();
		stocks = new HashMap<String, Float>();
	}
	
	public void changeQuote(StockEvent s) {
		stocks.put(s.getTicker(), s.getQuote());
	}

}
