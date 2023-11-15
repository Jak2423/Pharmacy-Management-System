package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.Stock;

public interface StockDao {

	public ArrayList<Stock> getStocks();

	public ArrayList<Stock> getMinCostStock();

	public ArrayList<Stock> getMaxCostStock();

	public ArrayList<Stock> getStockByName(String name);

	public void insertStock(Stock stock);

	public void deleteStock(int id);

	public void purchaseFromStock(int id);
}
