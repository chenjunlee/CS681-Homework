package edu.umb.cs681.hw19;

import java.util.ArrayList;

public class MainClient {

	public static void main(String[] args) {
		
		ArrayList<Car> usedCars = new ArrayList<Car>();
		
		usedCars.add(new Car(40000,2018,10000,"SUBARU","CROSSTREK"));
		usedCars.add(new Car(40000,2012,1050000,"SUBARU","OUTBACK"));
		usedCars.add(new Car(50000,2010,50000,"BMW","X5"));
		usedCars.add(new Car(20000,2017,30000,"Toyato","RAV4"));
		usedCars.add(new Car(10000,2003,75000,"Toyato","COROLLA"));
		usedCars.add(new Car(10000,2018,750,"Honda","CIVIC"));
		usedCars.add(new Car(30000,2018,750,"Honda","ACCORD"));
		usedCars.add(new Car(40000,2018,750,"Honda","CRV"));
		
		System.out.println("\n Inserted Car Price: \n");

		usedCars.forEach( (Car car)->System.out.println(car.getPrice()));
		System.out.println("\n \n min max count with reduce.......");
		
		long min = usedCars.stream().parallel().filter((Car car)->car.getPrice()<32000).count();
		System.out.println("The number of cars which price is lower than 32000: " + min);
		
		

		//Min with reduce
		Integer minPrice = usedCars.stream().parallel().map( (Car car)-> car.getPrice() )
												.reduce(0, (result, carPrice)->{
															if(result==0) return carPrice;
															else if(carPrice < result) return carPrice;
															else return result;} );
		System.out.println("\n Minimum car price is: " +minPrice);
		
		//Max with reduce
		Integer maxPrice = usedCars.stream().parallel().map( (Car car)-> car.getPrice() )
				.reduce(0, (result, carPrice)->{
							if(result==0) return carPrice;
							else if(carPrice < result) return result;
							else return carPrice;} );
		System.out.println("\n Maximum car price is: " +maxPrice);
		
		//Count with reduce	
		Integer Count = usedCars.stream().parallel().map( (Car car)-> car.getPrice() )
							.reduce((result, price)->{return result + price;}).get();
		System.out.println("\n Total cars value are: " +Count);
		
		long carModelNum = usedCars.stream().parallel().map((Car car)-> car.getModel())
				.reduce(0, (result, carModel)-> ++result,
							(finalResult, intermediateResult)->finalResult+intermediateResult);
		System.out.println("Total model number is (By using parallel stream and 3rd reduce): " + carModelNum);
				
	}
			
}
