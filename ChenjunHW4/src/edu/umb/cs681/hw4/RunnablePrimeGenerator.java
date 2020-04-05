package edu.umb.cs681.hw4;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/******************

20000000 is too big, it will run too long time to test
I use 20000

********************/

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		
//****************************************************************************************
		RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1, 20000);
		Thread t1 = new Thread(gen1);
		System.out.println("Number of Threds: "+ 1);
		Calendar cal1 = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {}
		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
	    Calendar calout1 = Calendar.getInstance();    
	    SimpleDateFormat sdfout = new SimpleDateFormat("HH:mm:ss");
		System.out.println("");
		System.out.println("Total time : " + (calout1.getTimeInMillis() - cal1.getTimeInMillis()));
//****************************************************************************************
		gen1 = new RunnablePrimeGenerator(1, 20000);
		RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(1, 20000);
		t1 = new Thread(gen1);
		Thread t2 = new Thread(gen2);
		System.out.println("Number of Threds: "+ 2);
		Calendar cal2 = Calendar.getInstance();
		sdf = new SimpleDateFormat("HH:mm:ss");
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {}
		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
	    Calendar calout2 = Calendar.getInstance();    
	    sdfout = new SimpleDateFormat("HH:mm:ss");
		System.out.println("");
		System.out.println("Total time : " + (calout2.getTimeInMillis() - cal2.getTimeInMillis()));		
//*********************************************************************************************		
		gen1 = new RunnablePrimeGenerator(1, 20000);
		gen2 = new RunnablePrimeGenerator(1, 20000);
		RunnablePrimeGenerator gen3 = new RunnablePrimeGenerator(1, 20000);
		RunnablePrimeGenerator gen4 = new RunnablePrimeGenerator(1, 20000);
		t1 = new Thread(gen1);
		t2 = new Thread(gen2);
		Thread t3 = new Thread(gen3);
		Thread t4 = new Thread(gen4);
		System.out.println("Number of Threds: "+ 4);
		Calendar cal4 = Calendar.getInstance();
		sdf = new SimpleDateFormat("HH:mm:ss");
		t1.start();
		t2.start();
		t3.start();
		t4.start();		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();		
		} catch (InterruptedException e) {}
		gen1.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen2.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen3.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
		gen4.getPrimes().forEach( (Long prime)->System.out.print(prime + ", ") );
	    Calendar calout4 = Calendar.getInstance();    
	    sdfout = new SimpleDateFormat("HH:mm:ss");
		System.out.println("");
		System.out.println("Total time : " + (calout4.getTimeInMillis() - cal4.getTimeInMillis()));	
	}

}
