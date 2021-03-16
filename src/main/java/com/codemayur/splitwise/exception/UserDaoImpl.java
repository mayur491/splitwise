package com.codemayur.splitwise.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.codemayur.splitwise.dao.impl.UserDao;
import com.codemayur.splitwise.model.User;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

	/**
	 * <p>
	 * {@code Key:} User's id
	 * </p>
	 * <p>
	 * {@code Value:} User's object
	 * </p>
	 */
	private Map<Integer, User> userMap;
	private Integer maxUserId;

	@Autowired
	public UserDaoImpl() {
		this.userMap = new HashMap<>();
		this.maxUserId = null;
	}

	@Override
	public User getUserById(Integer userId) {
		return userMap.get(userId);
	}

	@Override
	public void createUser(User user) {
		if (userMap.get(user.getId()) != null) {
			throw new IllegalStateException(String.format("User with id: %s already exists",
					user.getId()));
		}
		userMap.put(user.getId(),
				user);
	}

	@Override
	public Integer getNewUserId() {
		if (Objects.isNull(maxUserId)) {
			maxUserId = 0;
		}
		maxUserId = maxUserId + 1;
		return maxUserId;
	}

}
