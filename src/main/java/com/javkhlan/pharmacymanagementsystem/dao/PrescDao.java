package com.javkhlan.pharmacymanagementsystem.dao;

import java.util.ArrayList;

import com.javkhlan.pharmacymanagementsystem.model.Prescription;

public interface PrescDao {
	public ArrayList<Prescription> getPrescriptions();

	public void insertPrescription(Prescription pres);

	public void deletePrescription(int id);
}
