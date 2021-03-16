package com.codemayur.splitwise.dao;

import com.codemayur.splitwise.model.Bill;

public interface BillDao {

	public Bill getBillById(Integer billId);

	public void createBill(Bill bill);

	public Integer getNewBillId();

}
