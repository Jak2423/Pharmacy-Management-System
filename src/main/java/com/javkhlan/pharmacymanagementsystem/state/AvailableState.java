package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.Stock;

public class AvailableState extends StockState {
	public AvailableState(Stock stock) {
		super(stock);
	}

	@Override
	public void processStock() {
		stock.setStatus("available");
	}
}