package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
	private Address address;
	ReentrantLock lock = new ReentrantLock();
	public Customer(Address addr){ 
		this.address = addr; 
	}

	public Address getAddress() {
		lock.lock();
		try {
			System.out.println("Recent Address: "+address.toString());
			return address;
		}finally {
			lock.unlock();
		}
	}


	public void setAddress(Address address) {
		lock.lock();
		try {			
			this.address = address;
			System.out.println("new address: "+address.toString());
		}finally {
			lock.unlock();
		}
	}	
}
	
