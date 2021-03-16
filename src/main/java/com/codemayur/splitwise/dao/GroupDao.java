package com.codemayur.splitwise.dao;

import com.codemayur.splitwise.model.Group;

public interface GroupDao {

	public Group getGroupById(Integer groupId);

	public void createGroup(Group group);

	public Integer getNewGroupId();

}
