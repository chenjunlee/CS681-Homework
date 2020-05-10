package edu.umb.cs681.hw17;

public class DJIAEvent {
	private float djia;
	
	public DJIAEvent(float quote) {
		this.djia = quote;
	}
	
	public float getDJIA() {
		return djia;
	}
	
	public void setDJIA(float djia) {
		this.djia = djia;
	}
}
