package com.codemayur.splitwise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemayur.splitwise.dao.GroupDao;
import com.codemayur.splitwise.model.Group;
import com.codemayur.splitwise.service.GroupService;

@Service("groupServiceImpl")
public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;
	
	@Autowired
	public GroupServiceImpl(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	@Override
	public Group getGroupById(Integer groupId) {
		return groupDao.getGroupById(groupId);
	}

	@Override
	public void createGroup(String groupName) {
		Group group = new Group();
		synchronized (groupDao) {
			group.setId(groupDao.getNewGroupId());
		}
		group.setName(groupName);
		groupDao.createGroup(group);
	}

}
