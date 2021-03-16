package com.codemayur.splitwise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codemayur.splitwise.constant.UserConstant;
import com.codemayur.splitwise.model.User;
import com.codemayur.splitwise.service.UserService;
import com.codemayur.splitwise.validate.ValidateUser;

@Controller
@RequestMapping("user")
public class UserController {

	private ValidateUser validateUser;
	private UserService userService;

	@Autowired
	public UserController(
			ValidateUser validateUser,
			UserService userService) {
		this.validateUser = validateUser;
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getUser(@RequestParam("userId") Integer userId) {
		Map<String, Object> responseMap = new HashMap<>();
		User user = null;
		try {
			validateUser.validateUserId(userId);
			user = userService.getUserById(userId);
			if (user == null) {
				throw new IllegalStateException(String.format("User with id: %s doesn't exist",
						userId));
			}
		} catch (IllegalStateException e) {
			responseMap.put(UserConstant.SUCCESS,
					false);
			responseMap.put(UserConstant.MESSAGE,
					e.getMessage());
			responseMap.put(UserConstant.USER,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(UserConstant.SUCCESS,
					false);
			responseMap.put(UserConstant.MESSAGE,
					e.getMessage());
			responseMap.put(UserConstant.USER,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(UserConstant.SUCCESS,
				true);
		responseMap.put(UserConstant.MESSAGE,
				null);
		responseMap.put(UserConstant.USER,
				user);
		return new ResponseEntity<>(responseMap,
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createUser(@RequestParam("userName") String userName) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			validateUser.validateUserName(userName);
			userService.createUser(userName);
		} catch (IllegalStateException e) {
			responseMap.put(UserConstant.SUCCESS,
					false);
			responseMap.put(UserConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(UserConstant.SUCCESS,
					false);
			responseMap.put(UserConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(UserConstant.SUCCESS,
				true);
		responseMap.put(UserConstant.MESSAGE,
				null);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
