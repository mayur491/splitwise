package com.codemayur.splitwise.service;

import java.util.List;

import com.codemayur.splitwise.model.Group;

public interface GroupService {

	public Group getGroupById(Integer groupId);

	public void createGroup(String groupName);

	public void addMembersInGroup(Integer groupId,
			List<Integer> userIdList);

	public void deleteMembersFromGroup(Integer groupId,
			List<Integer> userIdList);

}
