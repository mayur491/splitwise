package com.codemayur.splitwise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemayur.splitwise.dao.BillDao;
import com.codemayur.splitwise.model.Bill;
import com.codemayur.splitwise.service.BillService;

@Service("billServiceImpl")
public class BillServiceImpl implements BillService {

	private BillDao billDao;

	@Autowired
	public BillServiceImpl(
			BillDao billDao) {
		this.billDao = billDao;
	}

	@Override
	public Bill getBillById(Integer billId) {
		Bill bill = billDao.getBillById(billId);
		if (bill == null) {
			throw new IllegalStateException(String.format("Bill with id: %s doesn't exists",
					billId));
		}
		return bill;
	}

	@Override
	public void createBill(String billName) {
		Bill bill = new Bill();
		synchronized (billDao) {
			bill.setId(billDao.getNewBillId());
		}
		bill.setName(billName);
		billDao.createBill(bill);
	}

}
