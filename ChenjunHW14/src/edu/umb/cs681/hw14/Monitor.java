package edu.umb.cs681.hw14;

public class Monitor implements Runnable {
	private AdmissionControl control;
	
	public Monitor(AdmissionControl control) {
		this.control = control;
	}
	
	public void run(){			
		control.countCurrentVisitors();
	}
}