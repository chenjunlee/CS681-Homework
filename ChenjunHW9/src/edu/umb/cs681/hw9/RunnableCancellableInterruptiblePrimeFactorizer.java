package edu.umb.cs681.hw9;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
	
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
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
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				System.out.println(e.toString());
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Factorization of 36");
		RunnableCancellableInterruptiblePrimeFactorizer runnable = new RunnableCancellableInterruptiblePrimeFactorizer(500, 2, (long)Math.sqrt(500));
		Thread thread = new Thread(runnable);
		System.out.println("Thread #" + thread.getId() + 
			" performs factorization in the range of " + runnable.getFrom() + "->" + runnable.getTo());
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		runnable.setDone();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final result: " + runnable.getPrimeFactors() + "\n");
	}
}