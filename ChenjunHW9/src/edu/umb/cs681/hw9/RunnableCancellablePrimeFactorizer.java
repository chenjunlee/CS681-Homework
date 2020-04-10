package edu.umb.cs681.hw9;

import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	
	private ReentrantLock lock;
	private boolean done;
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
		lock = new ReentrantLock();
		done = false;
	}
	
	public void setDone(){
		lock.lock();
		try { 
			done = true; 
		} finally {
			lock.unlock(); 
		} 
	}
	
	@Override
	public void generatePrimeFactors() {
		long divisor = from;
	    while(true){
			lock.lock();
			try {
				if(done)
					break;
				if(dividend == 1 || divisor > to)
					break;
				if( divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if(dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if(divisor==2) {
						divisor++;
					} else {
						divisor += 2;
					}
		    	}
			} finally {
				lock.unlock();
			}
		}
	}
	
	@Override
	public void run() {
		generatePrimeFactors();
		lock.lock();
		try {
			System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		// Factorization of 36 with a separate thread
		System.out.println("Factorization of 36");
		RunnableCancellablePrimeFactorizer runnable = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
		
		// Factorization of 84 with two threads
		System.out.println("Factorization of 84");
		LinkedList<RunnableCancellablePrimeFactorizer> runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
		LinkedList<Thread> threads = new LinkedList<Thread>();

		runnables.add( new RunnableCancellablePrimeFactorizer(84, 2, (long)Math.sqrt(84)/2 ));
		runnables.add( new RunnableCancellablePrimeFactorizer(84, 1+(long)Math.sqrt(84)/2, (long)Math.sqrt(84) ));
		
		thread = new Thread(runnables.get(0));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());
		
		thread = new Thread(runnables.get(1));
		threads.add(thread);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());
		
		threads.forEach( (t)->t.start() );
		threads.forEach( (t)->{	try{t.join();}
								catch(InterruptedException e){e.printStackTrace(); }} );
		
		LinkedList<Long> factors = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors + "\n");
		
		runnables.clear();
		threads.clear();
		

		RunnableCancellablePrimeFactorizer runnable1 = new RunnableCancellablePrimeFactorizer(2000000, 2, (long)Math.sqrt(2000000));
		Thread thread1 = new Thread(runnable1);
		System.out.println("Factorization of 2000000 with call setDone");
		thread1.start();
		runnable1.setDone();
		try {
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable1.getPrimeFactors() + "\n");
		
	}
}
