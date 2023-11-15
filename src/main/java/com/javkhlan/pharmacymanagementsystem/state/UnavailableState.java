package com.javkhlan.pharmacymanagementsystem.state;

import com.javkhlan.pharmacymanagementsystem.model.Stock;

public class UnavailableState extends StockState {

	public UnavailableState(Stock stock) {
		super(stock);
	}

	@Override
	public void processStock() {
		stock.setStatus("unavailable");
	}
}
