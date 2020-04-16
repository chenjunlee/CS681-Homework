package edu.umb.cs681.hw13;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();		
		int tcount = 5;
		
		ArrayList<Thread> threads = new ArrayList<Thread>(tcount);
		 for(int i = 0; i <tcount-1 ; i++){
			 Thread t1 = new Thread(new DepositRunnable(bankAccount));
			 threads.add(t1);
			 t1.start(); 
		 }
		
		Thread t1 = new Thread(new WithdrawRunnable(bankAccount));
		threads.add(t1);
		t1.start();
			 
		try {
			Thread.sleep(10);
		} catch(InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Main going to make it intrupt..threads.get(i).interrupt()");

		for(int i = 0; i < tcount; i++){	
			 threads.get(i).interrupt();
		}
		 
		System.out.println("going to set done..bankAccount.setDone()");
		bankAccount.setDone();
		System.out.println("set done finished..");
		 								
		for (int i = 0; i < tcount; i++){
			try {								
				threads.get(i).join();
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("Main end");	
	}
}
