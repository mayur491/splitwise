package com.codemayur.splitwise.dao;

import java.util.List;

import com.codemayur.splitwise.model.Group;
import com.codemayur.splitwise.model.User;

public interface GroupDao {

	public Group getGroupById(Integer groupId);

	public void createGroup(Group group);

	public Integer getNewGroupId();

	public void addMembersInGroup(Integer groupId,
			List<User> members);

	public void removeMembersFromGroup(Integer groupId,
			List<User> members);

}
