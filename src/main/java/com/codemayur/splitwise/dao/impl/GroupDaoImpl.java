package com.codemayur.splitwise.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codemayur.splitwise.dao.GroupDao;
import com.codemayur.splitwise.model.Group;
import com.codemayur.splitwise.model.User;

@Repository("groupDaoImpl")
public class GroupDaoImpl implements GroupDao {

	/**
	 * <p>
	 * {@code Key:} Group's id
	 * </p>
	 * <p>
	 * {@code Value:} Group's object
	 * </p>
	 */
	private Map<Integer, Group> groupMap;
	private Integer maxGroupId;

	@Autowired
	public GroupDaoImpl() {
		this.groupMap = new HashMap<>();
		this.maxGroupId = null;
	}

	@Override
	public Group getGroupById(Integer groupId) {
		return groupMap.get(groupId);
	}

	@Override
	public void createGroup(Group group) {
		if (groupMap.get(group.getId()) != null) {
			throw new IllegalStateException(String.format("Group with id: %s already exists",
					group.getId()));
		}
		groupMap.put(group.getId(),
				group);
	}

	@Override
	public Integer getNewGroupId() {
		if (Objects.isNull(maxGroupId)) {
			maxGroupId = 0;
		}
		maxGroupId = maxGroupId + 1;
		return maxGroupId;
	}

	@Override
	public void addMembersInGroup(Integer groupId,
			List<User> members) {
		Group group = groupMap.get(groupId);
		if (group == null) {
			throw new IllegalStateException(String.format("Group with id: %s doesn't exists",
					groupId));
		}
		
		List<User> list = group.getMembers();
		if(Objects.isNull(list) || list.isEmpty()) {
			group.setMembers(members);
		} else {
			for (User user : members) {
				if(!list.contains(user)) {
					list.add(user);
				}
			}
		}
	}

	@Override
	public void removeMembersFromGroup(Integer groupId,
			List<User> members) {
		Group group = groupMap.get(groupId);
		if (group == null) {
			throw new IllegalStateException(String.format("Group with id: %s doesn't exists",
					groupId));
		}
		
		List<User> list = group.getMembers();
		if(Objects.nonNull(list) && !list.isEmpty()) {
			for (User user : members) {
				if(list.contains(user)) {
					list.remove(user);
				}
			}
		}
	}

}
