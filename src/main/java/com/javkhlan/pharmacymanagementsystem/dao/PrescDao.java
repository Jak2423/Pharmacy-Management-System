package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.PrescriptionModel;

public interface PrescDao {
	public ArrayList<PrescriptionModel> getPrescriptions();

	public void insertPrescription(PrescriptionModel pres);

	public void deletePrescription(int id);
}
