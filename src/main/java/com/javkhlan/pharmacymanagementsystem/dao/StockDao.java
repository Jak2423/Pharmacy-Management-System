package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.StockModel;

public interface StockDao {

	public ArrayList<StockModel> getStocks();

	public ArrayList<StockModel> getMinCostStock();

	public ArrayList<StockModel> getMaxCostStock();

	public ArrayList<StockModel> getStockByName(String name);

	public void insertStock(StockModel stock);

	public void deleteStock(int id);

	public void purchaseFromStock(int id);
}
