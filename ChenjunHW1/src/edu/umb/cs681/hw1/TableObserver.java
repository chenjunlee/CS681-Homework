package edu.umb.cs681.hw1;

public class TableObserver implements Observer {

	public void update(Observable o, Object arg) {
		if (arg instanceof StockEvent) {
			String ticker = ((StockEvent) arg).getTicker();
			float quote = ((StockEvent) arg).getQuote();
			System.out.println("TableObserver:");
			System.out.println("Stock: " + ticker + " changed price " + quote + "!");
		}
		
		if (arg instanceof DJIAEvent) {
			float quote = ((DJIAEvent)arg).getDJIA();
			System.out.println("TableObserver: Djia " + quote);
		}
	}
}
