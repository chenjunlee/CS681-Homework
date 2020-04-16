package edu.umb.cs681.hw14;

public class Entrance implements Runnable {
	private AdmissionControl control;
	
	public Entrance(AdmissionControl control) {
		this.control = control;
	}
	
	public void run(){
		while(!control.getDone()){ 
			control.enter();
		}
		System.out.println(Thread.currentThread().getId() + " done = true");
	}
}