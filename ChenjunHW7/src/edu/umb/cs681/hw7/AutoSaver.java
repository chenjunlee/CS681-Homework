package edu.umb.cs681.hw7;

public class AutoSaver implements Runnable{
	
	private boolean done;
	private File file;
	
	public AutoSaver(File file) {
		this.file = file;
		this.done = false;
	}
	
	@Override
	public void run() 
	{
		file.change("Auto Saver!!! ");
		file.save();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) 
		{	
			e.printStackTrace();
		}
	}

	public void setDone()
	{
		done = true;
	}

}
