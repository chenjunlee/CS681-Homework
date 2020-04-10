package edu.umb.cs681.hw10;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable {
	private ConcurrentSingleton(){};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(null); 

	private static ReentrantLock lock = new ReentrantLock();

	public static ConcurrentSingleton getInstance()
	{
			if(instance.get()==null)
				instance.set(new ConcurrentSingleton());
		return instance.get();
	}

	public static void main(String args[])
	{
		ConcurrentSingleton instance1 = ConcurrentSingleton.getInstance();
		ConcurrentSingleton instance2 = ConcurrentSingleton.getInstance();
		ConcurrentSingleton instance3 = ConcurrentSingleton.getInstance();
		Thread thread1 = new Thread(instance1);
		Thread thread2 = new Thread(instance2);
		Thread thread3 = new Thread(instance3);
		thread1.start();
		thread2.start();
		thread3.start();
		try
		{
			thread1.join();
			thread2.join();
			thread3.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void run() 
	{
		System.out.println(ConcurrentSingleton.getInstance());
	}
}
