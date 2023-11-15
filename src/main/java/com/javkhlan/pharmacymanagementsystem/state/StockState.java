package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.StockModel;

public abstract class StockState {
	StockModel stock;

	StockState(StockModel stock) {
		this.stock = stock;
	}

	public abstract void processStock();
}