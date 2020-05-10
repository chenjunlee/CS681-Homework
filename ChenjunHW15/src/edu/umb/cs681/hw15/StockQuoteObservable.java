package edu.umb.cs681.hw15;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable {
	
	HashMap<String, Float> stocks;
	private ReentrantLock lockA = new ReentrantLock();
	
	public StockQuoteObservable() {
		super();
		stocks = new HashMap<String, Float>();
	}
	
	public HashMap<String, Float> getMap() {
		lockA.lock();
		try {
			return stocks;
		}finally {
			lockA.unlock();
		}
	}
	
	public void changeQuote(StockEvent s) {
		lockA.lock();
		try{
		stocks.put(s.getTicker(), s.getQuote());
		notifyObservers(s);
		} finally {
			lockA.unlock();
		}
	}

}
