package edu.umb.cs681.hw15;

public interface Observer {
	public abstract void update(Observable o, Object arg);
}