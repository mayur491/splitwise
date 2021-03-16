package com.codemayur.splitwise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.codemayur.splitwise.dao.GroupDao;
import com.codemayur.splitwise.model.Group;
import com.codemayur.splitwise.model.User;
import com.codemayur.splitwise.service.GroupService;
import com.codemayur.splitwise.service.UserService;

@Service("groupServiceImpl")
public class GroupServiceImpl implements GroupService {

	private GroupDao groupDao;
	private UserService userService;

	@Autowired
	public GroupServiceImpl(GroupDao groupDao,
			@Qualifier("userServiceImpl") UserService userService) {
		this.groupDao = groupDao;
		this.userService = userService;
	}

	@Override
	public Group getGroupById(Integer groupId) {
		Group group = groupDao.getGroupById(groupId);
		if (group == null) {
			throw new IllegalStateException(String.format("Group with id: %s doesn't exist",
					groupId));
		}
		return group;
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

	@Override
	public void addMembersInGroup(Integer groupId,
			List<Integer> userIdList) {
		List<User> members = new ArrayList<>();
		for (Integer userId : userIdList) {
			User user = userService.getUserById(userId);
			members.add(user);
		}
		groupDao.addMembersInGroup(groupId, members);
	}

	@Override
	public void deleteMembersFromGroup(Integer groupId,
			List<Integer> userIdList) {
		List<User> members = new ArrayList<>();
		for (Integer userId : userIdList) {
			User user = userService.getUserById(userId);
			members.add(user);
		}
		groupDao.removeMembersFromGroup(groupId, members);
	}

}
