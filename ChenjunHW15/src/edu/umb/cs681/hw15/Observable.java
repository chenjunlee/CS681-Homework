package edu.umb.cs681.hw15;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class Observable {
	private Set<Observer> set;
	private volatile boolean hasChanged = false;
	private ReentrantLock lockObs = new ReentrantLock();
	
	public Observable() {

		set = new HashSet();
		hasChanged = false;
	}
	
	public void addObserver(Observer o) {
		lockObs.lock();
		try{
			if(o != null) {
				System.out.println("Add success");
				set.add(o);
			}
		} finally {
			lockObs.unlock();
		}
	}
	
	public int countObservers() {
		return set.size();
	}
	
	public void deleteObserver(Observer o) {
		lockObs.lock();
		try {
			set.remove(o);
		} finally {
			lockObs.unlock();
		}
	}
	
	public void deleteObservers() {
		lockObs.lock();
		try {
			set.clear();
		} finally {
			lockObs.unlock();
		}
	}
	
	public boolean hasChanged() {
		return hasChanged;
	}
	
	public void notifyObservers(){
		return;
	}
	
	public void notifyObservers(Object arg) {
		lockObs.lock();
		try {
			if(hasChanged())
				for(Observer o1 : set)
					o1.update(this, arg);
			hasChanged = false;
		} finally {
			lockObs.unlock();
		}
	}
	
	protected void setChanged() {
		hasChanged = true;
	}
}