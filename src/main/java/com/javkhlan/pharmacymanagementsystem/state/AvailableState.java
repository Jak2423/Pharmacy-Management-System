package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.StockModel;

public class AvailableState extends StockState {
	public AvailableState(StockModel stock) {
		super(stock);
	}

	@Override
	public void processStock() {
		stock.setStatus("available");
	}
}