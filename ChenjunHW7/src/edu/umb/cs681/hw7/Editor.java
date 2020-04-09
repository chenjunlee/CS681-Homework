package edu.umb.cs681.hw7;

public class Editor implements Runnable
{
	private boolean done = false;
	private File file;
	
	public Editor(File file) {
		this.file = file;
		done = false;
	}

	@Override
	public void run() 
	{
		while(true)
		{
			if(done==true)
			{
				System.out.print("Editor set done!!");
				break;
			}
			file.change("Editor changed it!! ");
			file.save();
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}	
		}
	}
	public void setDone()
	{
		done  = true;
	}
}
