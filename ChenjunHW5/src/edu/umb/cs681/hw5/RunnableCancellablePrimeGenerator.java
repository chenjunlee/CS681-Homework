package edu.umb.cs681.hw5;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator	extends RunnablePrimeGenerator {
	
	private ReentrantLock lock;
	private boolean done;
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
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
	public void generatePrimes(){
		for(long n = from; n <= to; n++){
			lock.lock();
			try{
				if(done) 
					break;  // Balking 
				if(isPrime(n)){
					this.primes.add(n);
				} 
			}finally{
				lock.unlock();
			} 
		}
	}
	
	public void run() {
		generatePrimes();
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,1000000);
		Thread thread = new Thread(gen);
		thread.start();
		try {
			System.out.println("----------------Main--------------------------");
			System.out.println("Main thred sleeping for 10ms then will do setDone");
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------Main---------------------------");
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
