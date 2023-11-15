package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.StockModel;

public class UnavailableState extends StockState {

	public UnavailableState(StockModel stock) {
		super(stock);
	}

	@Override
	public void processStock() {
		stock.setStatus("unavailable");
	}
}
