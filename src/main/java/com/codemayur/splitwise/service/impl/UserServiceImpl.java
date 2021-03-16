package com.codemayur.splitwise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codemayur.splitwise.dao.impl.UserDao;
import com.codemayur.splitwise.model.User;
import com.codemayur.splitwise.service.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User getUserById(Integer userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public void createUser(String userName) {
		User user = new User();
		synchronized (userDao) {
			user.setId(userDao.getNewUserId());
		}
		user.setName(userName);
		userDao.createUser(user);
	}

}
