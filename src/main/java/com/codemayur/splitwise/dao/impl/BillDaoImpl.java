package com.codemayur.splitwise.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codemayur.splitwise.dao.BillDao;
import com.codemayur.splitwise.model.Bill;

@Repository("billDaoImpl")
public class BillDaoImpl implements BillDao {

	/**
	 * <p>
	 * {@code Key:} Bill's id
	 * </p>
	 * <p>
	 * {@code Value:} Bill's object
	 * </p>
	 */
	private Map<Integer, Bill> billMap;
	private Integer maxBillId;

	@Autowired
	public BillDaoImpl() {
		this.billMap = new HashMap<>();
		this.maxBillId = null;
	}

	@Override
	public Bill getBillById(Integer billId) {
		return billMap.get(billId);
	}

	@Override
	public void createBill(Bill bill) {
		if (billMap.get(bill.getId()) != null) {
			throw new IllegalStateException(String.format("Bill with id: %s already exists",
					bill.getId()));
		}
		billMap.put(bill.getId(),
				bill);
	}

	@Override
	public Integer getNewBillId() {
		if (Objects.isNull(maxBillId)) {
			maxBillId = 0;
		}
		maxBillId = maxBillId + 1;
		return maxBillId;
	}

}
