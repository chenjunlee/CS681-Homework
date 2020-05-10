package edu.umb.cs681.hw17;

public class StockMain{
	public static void main(String[] args){
		StockQuoteObservable myStock = new StockQuoteObservable();

		System.out.println("I'm running");
		TableObserver table = new TableObserver();
		PiechartObserver pieChart = new PiechartObserver();
		ThreeDObserver threeD = new ThreeDObserver();
		myStock.addObserver((Observable o, Object obj) -> {System.out.println("Just a test of Lambda Expression!!!");});
		myStock.addObserver(table);
		myStock.addObserver(threeD);
		myStock.addObserver(pieChart);

		StockEvent event = new StockEvent("GM", 5.11f);		
		myStock.changeQuote(event);
		myStock.setChanged();
		myStock.notifyObservers(event);
	}
}
