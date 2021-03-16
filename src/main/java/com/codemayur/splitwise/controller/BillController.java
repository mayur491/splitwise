package com.codemayur.splitwise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemayur.splitwise.constant.BillConstant;
import com.codemayur.splitwise.model.Bill;
import com.codemayur.splitwise.service.BillService;
import com.codemayur.splitwise.validate.ValidateBill;

@Controller
@RequestMapping("bill")
public class BillController {

	private ValidateBill validateBill;
	private BillService billService;

	@Autowired
	public BillController(ValidateBill validateBill,
			BillService billService) {
		this.validateBill = validateBill;
		this.billService = billService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getBill(@RequestParam("billId") Integer billId) {
		Map<String, Object> responseMap = new HashMap<>();
		Bill bill = null;
		try {

			validateBill.validateBillId(billId);
			bill = billService.getBillById(billId);

		} catch (IllegalStateException e) {
			responseMap.put(BillConstant.SUCCESS,
					false);
			responseMap.put(BillConstant.MESSAGE,
					e.getMessage());
			responseMap.put(BillConstant.BILL,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(BillConstant.SUCCESS,
					false);
			responseMap.put(BillConstant.MESSAGE,
					e.getMessage());
			responseMap.put(BillConstant.BILL,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(BillConstant.SUCCESS,
				true);
		responseMap.put(BillConstant.MESSAGE,
				null);
		responseMap.put(BillConstant.BILL,
				bill);
		return new ResponseEntity<>(responseMap,
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createBill(@RequestParam("billName") String billName) {
		Map<String, Object> responseMap = new HashMap<>();
		try {

			validateBill.validateBillName(billName);
			billService.createBill(billName);

		} catch (IllegalStateException e) {
			responseMap.put(BillConstant.SUCCESS,
					false);
			responseMap.put(BillConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(BillConstant.SUCCESS,
					false);
			responseMap.put(BillConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(BillConstant.SUCCESS,
				true);
		responseMap.put(BillConstant.MESSAGE,
				null);
		return new ResponseEntity<>(responseMap,
				HttpStatus.OK);
	}

}
