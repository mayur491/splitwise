package com.codemayur.splitwise.dao.impl;

import com.codemayur.splitwise.model.User;

public interface UserDao {

	public User getUserById(Integer userId);

	public void createUser(User user);

	public Integer getNewUserId();

}
