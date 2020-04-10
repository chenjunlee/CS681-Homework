package edu.umb.cs681.hw11;

public class Main implements Runnable{

	public static void main(String[] args) {
		
		Main main1 = new Main();
		Main main2 = new Main();
		Thread thread1 = new Thread(main1);
		Thread thread2 = new Thread(main2);
		
		thread1.start();
		thread2.start();

		try {								
			thread1.join();
			thread2.join();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		//....................
		System.out.println("Main end stamement....");
		
	}
	
	public void run() {
		Address add1 = new Address("Revere Beach Blvd","Revere","MA", 02151);
		Customer customer = new Customer(add1);
		customer.getAddress().toString();
		customer.setAddress( new Address ("Peninsula Place", "Dorchester", "MA", 02125));
		customer.getAddress().toString();
		customer.setAddress(customer.getAddress().change("Harbor Point Blvd", "Dorchester", "MA", 02125));
		customer.getAddress().toString();
	}
}
