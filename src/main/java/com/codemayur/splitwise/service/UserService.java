package com.codemayur.splitwise.service;

import com.codemayur.splitwise.model.User;

public interface UserService {

	public User getUserById(Integer userId);

	public void createUser(String userName);

}
