package edu.umb.cs681.hw16;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class AccessCounter {

	private ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap<Path, AtomicInteger>();
	private static ReentrantLock lock = new ReentrantLock();
	private static AccessCounter instance = null;

	private AccessCounter() {
		
	}

	public static AccessCounter getInstance() {
		lock.lock();
		try {
			if(instance==null){
				instance = new AccessCounter();
			}
		} finally {
			lock.unlock();
		}
		return instance;
	}


	public void increment(Path path) {
		
		map.putIfAbsent(path, new AtomicInteger(0));
		map.get(path).incrementAndGet();		
		
	}

	public AtomicInteger getCount(Path path) {		
			return map.get(path);
	}

	public static void main(String args[]) {
		RequestHandler request1 = new RequestHandler();

		Thread thread1 = new Thread(request1);
		Thread thread2 = new Thread(request1);
		Thread thread3 = new Thread(request1);
		Thread thread4 = new Thread(request1);
		Thread thread5 = new Thread(request1);
		Thread thread6 = new Thread(request1);
		Thread thread7 = new Thread(request1);
		Thread thread8 = new Thread(request1);
		Thread thread9 = new Thread(request1);
		Thread thread10 = new Thread(request1);
		Thread thread11 = new Thread(request1);
		Thread thread12 = new Thread(request1);
		Thread thread13 = new Thread(request1);
		Thread thread14 = new Thread(request1);
		Thread thread15 = new Thread(request1);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();
		thread13.start();
		thread14.start();
		thread15.start();
		
		try{
			Thread.sleep(2000);
		}catch (InterruptedException e) {}
		thread1.interrupt();
		thread2.interrupt();
		thread3.interrupt();
		thread4.interrupt();
		thread5.interrupt();
		thread6.interrupt();
		thread7.interrupt();
		thread8.interrupt();
		thread9.interrupt();
		thread10.interrupt();
		thread11.interrupt();
		thread12.interrupt();
		thread13.interrupt();
		thread14.interrupt();
		thread15.interrupt();
		request1.setDone();
	}
}
