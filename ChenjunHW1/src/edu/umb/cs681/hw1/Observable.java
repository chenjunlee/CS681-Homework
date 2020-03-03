package edu.umb.cs681.hw1;
import java.util.Set;
import java.util.HashSet;

public class Observable {
	private Set<Observer> set;
	private boolean hasChanged;
	
	public Observable() {
		set = new HashSet();
		hasChanged = false;
	}
	
	public void addObserver(Observer o) {
		System.out.println("Add success");
		set.add(o);
	}
	
	public int countObservers() {
		return set.size();
	}
	
	public void deleteObserver(Observer o) {
		set.remove(o);
	}
	
	public void deleteObservers() {
		set.clear();
	}
	
	public boolean hasChanged() {
		return hasChanged;
	}
	
	public void notifyObservers(){
		
	}
	
	public void notifyObservers(Object arg) {
		if(hasChanged())
			for(Observer o1 : set)
				o1.update(this, arg);
		hasChanged = false;
	}
	
	protected void setChanged() {
		hasChanged = true;
	}
}