package edu.umb.cs681.hw17;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Observable {
	private ConcurrentLinkedQueue<Observer> observers;
	private volatile boolean hasChanged = false;
	private ReentrantLock lockObs = new ReentrantLock();
	
	public Observable() {
		observers = new ConcurrentLinkedQueue<Observer>();
	}
	
	public void addObserver(Observer o) {
		if (o == null) //o is local variable..
			throw new NullPointerException("can't add null observer");
		observers.add(o);
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public void deleteObserver(Observer o) {
		observers.remove(o);
	}
	
	public void deleteObservers() {
		observers.clear();
	}
	
	public boolean hasChanged() {
		return hasChanged;
	}
	
	public void notifyObservers(){
		return;
	}
	
	public void notifyObservers(Object arg) {

		if(hasChanged())
			for(Observer o1 : observers)
					o1.update(this, arg);
			hasChanged = false;
	}
	
	protected void setChanged() {
		hasChanged = true;
	}
}