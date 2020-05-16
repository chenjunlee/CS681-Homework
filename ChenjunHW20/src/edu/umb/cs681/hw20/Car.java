package edu.umb.cs681.hw20;
import java.lang.Math;

public class Car {

	private String make, model;
	private int mileage, year;
	private float price;
	private int domination;
	
	public Car(String make, String model, int mileage, int year, float price) {
		
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
		this.domination = 0;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}
	
	public int getYear() {
		return year;
	}
	
	public float getPrice() {
		return price;
	}
	
	
	public int getDomination(Car car) {
		int mileDiff = this.mileage - car.getMileage();
		float priceDiff = this.price - car.getPrice();
		int yearDiff = this.year - car.getYear();
		float result = (float)(0.5 * mileDiff + 0.5 * priceDiff + 600 * yearDiff);
		return Math.abs((int)result);
	}
	
}