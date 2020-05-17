package edu.umb.cs681.hw21;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 

public class AccessCounter {

	private HashMap<Path, Integer> map;
	private ReentrantLock lock = new ReentrantLock();
	private static AccessCounter instance = null;

	private AccessCounter()
	{
		map = new HashMap<>();
	}

	public static AccessCounter getInstance()
	{
		if(instance==null)
		{
			instance = new AccessCounter();
		}
		return instance;
	}


	public void increment(Path path)
	{
		if(map.containsKey(path))
		{	
			Set<Path> keys = map.keySet();
			for (Path key : keys) {
				lock.lock();
				try {
					if(key.equals(path))
					{
						Integer value = map.get(key) + 1;
						map.put(key, value);
						System.out.println("Add path: " + path.getFileName() + " one more access");
					}
					else{
						continue;
					}
				}
				finally
				{
					lock.unlock();
				}
			}
		}
		else
		{
			lock.lock();
			try{
				map.put(path, 1);
				System.out.println("Add path: " + path.getFileName() +  " one more access");
			}
			finally{
				lock.unlock();
			}
		}
	}

	public int getCount(Path path)
	{
		int count = 0; 
		if(map.containsKey(path))
		{
			Set<Entry<Path, Integer>> entrySet = map.entrySet();
			for(Entry<Path, Integer> entry: entrySet)
			{
				lock.lock();
				try{
					if(entry.getKey().equals(path))
					{
						count = entry.getValue();
						System.out.println("Count for file "+entry.getKey().getFileName()+" is "+count);
					}
				}finally{
					lock.unlock();
				}
			}
		}
		else
		{
			lock.lock();
			try{
				System.out.println(count);
			}
			finally{
				lock.unlock();
			}
		}
		return count;
	}

	public static void main(String args[])
	{
		RequestHandler request1 = new RequestHandler();
		RequestHandler request2 = new RequestHandler();
		RequestHandler request3 = new RequestHandler();
		RequestHandler request4 = new RequestHandler();
		RequestHandler request5 = new RequestHandler();
		RequestHandler request6 = new RequestHandler();
		RequestHandler request7 = new RequestHandler();
		RequestHandler request8 = new RequestHandler();
		RequestHandler request9 = new RequestHandler();
		RequestHandler request10 = new RequestHandler();

		ExecutorService pool = Executors.newFixedThreadPool(12);
		
		pool.execute(request1); 
        pool.execute(request2); 
        pool.execute(request3); 
        pool.execute(request4); 
        pool.execute(request5);
		pool.execute(request6); 
        pool.execute(request7); 
        pool.execute(request8); 
        pool.execute(request9); 
        pool.execute(request10);
		
		pool.shutdown();
	}
}
