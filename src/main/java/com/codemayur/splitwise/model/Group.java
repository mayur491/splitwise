package com.codemayur.splitwise.model;

import java.util.List;

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
public class Group {

	@NonNull
	private Integer id;
	@NonNull
	private String name;

	/**
	 * <p>
	 * List of all users present in the group.
	 * </p>
	 */
	private List<User> members;

}
