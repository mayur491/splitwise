package com.codemayur.splitwise.service;

import com.codemayur.splitwise.model.Group;

public interface GroupService {

	public Group getGroupById(Integer groupId);

	public void createGroup(String groupName);

}
