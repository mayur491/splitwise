package com.codemayur.splitwise.model;

import java.util.Map;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bill {

	@NonNull
	private Integer id;
	private String amount;
	private String groupId;

	/**
	 * <p>
	 * {@code Key:} User's id
	 * </p>
	 * <p>
	 * {@code Value:} Amount to be paid by the user
	 * </p>
	 */
	private Map<Integer, String> amountToBePaid;

	/**
	 * <p>
	 * {@code Key:} User's id
	 * </p>
	 * <p>
	 * {@code Value:} Amount paid by the user
	 * </p>
	 */
	private Map<Integer, String> amountPaid;

}
