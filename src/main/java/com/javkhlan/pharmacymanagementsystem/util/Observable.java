package com.javkhlan.pharmacymanagementsystem.util;

public interface Observable {
	public void register(Observer obj);

	public void unregister(Observer obj);

	public void notifyObservers();

}
