package edu.umb.cs681.hw8;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable {
	private ConcurrentSingleton(){};
	private static ConcurrentSingleton instance = null;

	private static ReentrantLock lock = new ReentrantLock();

	public static ConcurrentSingleton getInstance()
	{
		lock.lock();
		try
		{
			if(instance==null)
				instance = new ConcurrentSingleton();
		}
		finally
		{
			lock.unlock();
		}
		return instance;
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
