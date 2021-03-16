package com.codemayur.splitwise.service;

import com.codemayur.splitwise.model.Bill;

public interface BillService {

	public Bill getBillById(Integer billId);

	public void createBill(String billName);

}
