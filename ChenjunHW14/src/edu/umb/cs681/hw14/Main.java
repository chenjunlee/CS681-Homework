package edu.umb.cs681.hw14;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		AdmissionControl admission = new AdmissionControl();		
		int count = 6;
		ArrayList<Thread> threads = new ArrayList<Thread>();

		for (int i = 0; i <3 ; i++){
			Thread t = new Thread(new Entrance(admission));
			threads.add(t);
			t.start();
		}
		 
		Thread t1 = new Thread(new Monitor(admission));
		threads.add(t1);
		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
			 
		
		for (int i = 0; i <2 ; i++){
			Thread t2 = new Thread(new Exit(admission));
			threads.add(t2);
			t2.start(); 
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}	
		System.out.println("Main: going to make it intrupt..threads.get(i).interrupt()");

		for (Thread temp : threads){
			temp.interrupt();
		}

		System.out.println("Main: going to set done..admissionC.setDone()");
		admission.setDone();
		System.out.println("Main: set done finished..");
			
		for (Thread temp : threads) {
			try {								
				temp.join();
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
			
		System.out.println("Main: Increment Decremnt Threads are done....Both Gates are Closed.........");
		admission.resetDone();
		Thread t3 = new Thread(new Monitor(admission));		
		t3.start();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}		 
		admission.setDone();
		System.out.println("Main: here |#incremented statements- #decremented statements| = |Last visitor Reading| ");
	}
}
