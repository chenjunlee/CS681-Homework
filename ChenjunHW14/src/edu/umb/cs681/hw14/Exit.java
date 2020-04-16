package edu.umb.cs681.hw14;

public class Exit implements Runnable {
	private AdmissionControl control;
	
	public Exit(AdmissionControl control) {
		this.control = control;
	}

	public void run(){
		while(!control.getDone()){
			control.exit();// two thread might concurently execute this statement but it as expected..					
		}
		System.out.println(Thread.currentThread().getId() + " done = true");
	}
}