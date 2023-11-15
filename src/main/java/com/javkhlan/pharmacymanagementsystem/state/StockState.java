package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.Stock;

public abstract class StockState {
	Stock stock;

	StockState(Stock stock) {
		this.stock = stock;
	}

	public abstract void processStock();
}