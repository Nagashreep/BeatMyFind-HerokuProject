package com.shrishti.siri.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shrishti.siri.dataAccessDelegate.entity.UserDetails;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;
import com.shrishti.siri.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/saveUser")
	public UserDetails saveUser(@RequestBody UserDetails user){
		System.out.println("user in saveUser: "+user.toString());
		return userService.saveUser(user);
		
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public UserDetails authenticate(@RequestBody UserDetails user){
		System.out.println("User in UserController: "+user);
		if(user!=null){
			System.out.println("User: "+user.toString());
		}
		return userService.authenticate(user);
		
	}
	
	@RequestMapping(value="/isUserNameUnique", method=RequestMethod.POST)
	public boolean isUserNameUnique(@RequestBody String userName){
		Map<String,Boolean> resultantMap = new HashMap<String, Boolean>();
		
		System.out.println("userName in UserController: "+userName);
		
		return userService.isUserNameUnique(userName);
	}
	
	@RequestMapping(value="/fetchUserQueries", method=RequestMethod.POST)
	public List<UserQuery> fetchUserQueries(@RequestBody int userId){
		return userService.fetchUserQueries(userId);
	}
	
	@RequestMapping(value="/closeUserQueries", method=RequestMethod.POST)
	public List<UserQuery> closeUserQueries(@RequestBody String[] queryIdsToClose){
		System.out.println("queryIdsToClose>> "+queryIdsToClose);
		return userService.closeUserQueries(queryIdsToClose);
	}
	
	
}
