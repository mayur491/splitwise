package com.codemayur.splitwise.dao.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codemayur.splitwise.dao.GroupDao;
import com.codemayur.splitwise.model.Group;

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

}
