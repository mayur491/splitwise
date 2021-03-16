package com.codemayur.splitwise.validate;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ValidateUser {

	@Autowired
	public ValidateUser() {
		// nothing
	}

	public void validateUserId(Integer userId) {
		if (Objects.isNull(userId)) {
			throw new IllegalStateException("User id cannot be null");
		}
	}

	public void validateUserName(String userName) {
		if (Objects.isNull(userName)) {
			throw new IllegalStateException("User name cannot be null");
		}
		if (ObjectUtils.isEmpty(userName)) {
			throw new IllegalStateException("User name cannot be empty");
		}
	}

}
