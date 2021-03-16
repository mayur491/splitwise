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

import com.codemayur.splitwise.constant.GroupConstant;
import com.codemayur.splitwise.model.Group;
import com.codemayur.splitwise.service.GroupService;
import com.codemayur.splitwise.validate.ValidateGroup;

@Controller
@RequestMapping("group")
public class GroupController {

	private ValidateGroup validateGroup;
	private GroupService groupService;

	@Autowired
	public GroupController(
			ValidateGroup validateGroup,
			GroupService groupService) {
		this.validateGroup = validateGroup;
		this.groupService = groupService;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> getGroup(@RequestParam("groupId") Integer groupId) {
		Map<String, Object> responseMap = new HashMap<>();
		Group group = null;
		try {
			validateGroup.validateGroupId(groupId);
			group = groupService.getGroupById(groupId);
			if (group == null) {
				throw new IllegalStateException(String.format("Group with id: %s doesn't exist",
						groupId));
			}
		} catch (IllegalStateException e) {
			responseMap.put(GroupConstant.SUCCESS,
					false);
			responseMap.put(GroupConstant.MESSAGE,
					e.getMessage());
			responseMap.put(GroupConstant.GROUP,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(GroupConstant.SUCCESS,
					false);
			responseMap.put(GroupConstant.MESSAGE,
					e.getMessage());
			responseMap.put(GroupConstant.GROUP,
					null);
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(GroupConstant.SUCCESS,
				true);
		responseMap.put(GroupConstant.MESSAGE,
				null);
		responseMap.put(GroupConstant.GROUP,
				group);
		return new ResponseEntity<>(responseMap,
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> createGroup(@RequestParam("groupName") String groupName) {
		Map<String, Object> responseMap = new HashMap<>();
		try {
			validateGroup.validateGroupName(groupName);
			groupService.createGroup(groupName);
		} catch (IllegalStateException e) {
			responseMap.put(GroupConstant.SUCCESS,
					false);
			responseMap.put(GroupConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			responseMap.put(GroupConstant.SUCCESS,
					false);
			responseMap.put(GroupConstant.MESSAGE,
					e.getMessage());
			return new ResponseEntity<>(responseMap,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		responseMap.put(GroupConstant.SUCCESS,
				true);
		responseMap.put(GroupConstant.MESSAGE,
				null);
		return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}

}
