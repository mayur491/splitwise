package com.codemayur.splitwise.validate;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ValidateBill {

	@Autowired
	public ValidateBill() {
		// nothing
	}

	public void validateBillId(Integer billId) {
		if (Objects.isNull(billId)) {
			throw new IllegalStateException("Bill id cannot be null");
		}
	}

	public void validateBillName(String billName) {
		if (Objects.isNull(billName)) {
			throw new IllegalStateException("Bill name cannot be null");
		}
		if (ObjectUtils.isEmpty(billName)) {
			throw new IllegalStateException("Bill name cannot be empty");
		}
	}

}
