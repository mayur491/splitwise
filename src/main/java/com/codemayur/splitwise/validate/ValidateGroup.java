package com.codemayur.splitwise.validate;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class ValidateGroup {

	private ValidateUser validateUser;

	@Autowired
	public ValidateGroup(ValidateUser validateUser) {
		this.validateUser = validateUser;
	}

	public void validateGroupId(Integer groupId) {
		if (Objects.isNull(groupId)) {
			throw new IllegalStateException("Group id cannot be null");
		}
	}

	public void validateGroupName(String groupName) {
		if (Objects.isNull(groupName)) {
			throw new IllegalStateException("Group name cannot be null");
		}
		if (ObjectUtils.isEmpty(groupName)) {
			throw new IllegalStateException("Group name cannot be empty");
		}
	}

	public void validateUserIdList(List<Integer> userIdList) {
		for (Integer userId : userIdList) {
			validateUser.validateUserId(userId);
		}
	}

}
