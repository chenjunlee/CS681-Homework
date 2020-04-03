package edu.umb.cs681.hw3;

import java.util.ArrayList;

public class MainClient {

	public static void main(String[] args) {
		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car("SUBARU", "CROSSTREK", 10000, 2018, 20000));
		usedCars.add(new Car("SUBARU", "OUTBACK", 105000, 2012, 8000));
		usedCars.add(new Car("BMW", "X5", 56000, 2016, 16000));
		usedCars.add(new Car("Toyato", "RAV4", 30000, 2014, 13000));
		usedCars.add(new Car("Toyato", "RAV4", 50000, 2013, 11000));
		usedCars.add(new Car("Honda", "CRV", 50000, 2013, 11000));
		usedCars.add(new Car("Honda", "CIVIC", 120000, 2001, 3000));
		usedCars.add(new Car("Honda", "ACCROD", 130000, 2002, 3000));
		
		for(Car car : usedCars) {
			System.out.println("Car maker is: " + car.getMake());
			System.out.println("Car modle is: " + car.getModel());
			System.out.println("Car miles is: " + car.getMileage());
			System.out.println("Car year is: " + car.getYear());
			System.out.println("Car price is: " + car.getPrice());
			System.out.println("***************************");
		}
		
		Car targetCar = new Car("Honda", "ACCROD", 130000, 2003, 3000);
		
		float minDiff = usedCars.stream().map( (Car usedCar)-> usedCar.getDomination(targetCar) )
											.reduce(500000, (result, carDifference)->{
															if(result==500000) return carDifference;
															else if(carDifference < result) return carDifference;
															else return result;} );
		System.out.println("Car difference is calculated by 50%price + 50%mileage + 600*year");
		System.out.println("\n Minimum differece car from target car is: " + minDiff);

	}
			
}
