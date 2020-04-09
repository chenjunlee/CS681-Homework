package edu.umb.cs681.hw7;

import java.util.concurrent.locks.ReentrantLock;

public class File {
	private boolean changed;
	private ReentrantLock lock;
	private String string;
	
	public File() {
		this.changed = false;
		this.lock = new ReentrantLock();
		this.string = "";
	}

	public static void main(String[] args) {
		File aFile = new File();
		Editor editor = new Editor(aFile);
		AutoSaver autoSaver = new AutoSaver(aFile);
		Thread thread1 = new Thread(editor);
		Thread thread2 = new Thread(autoSaver);
		thread1.start();
		thread2.start();
		try {
			System.out.println("----------------Main--------------------------");
			System.out.println("Main thred sleeping for 10ms then will do setDone");
			Thread.sleep(10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		editor.setDone();
		autoSaver.setDone();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		aFile.getString();
	}

	public void change(String string)
	{
		lock.lock();
		try{
			changed = true;
			this.string += string;
		}
		finally
		{
			lock.unlock();
		}
	}

	public void getString(){
		System.out.println(this.string);
	}

	public void save()
	{
		lock.lock();
		try{
			if(changed == false)
				return;
			else{
				System.out.println("Saved !!!");
				changed = false;
			}
		}
		finally
		{
			lock.unlock();
		}
	}
}