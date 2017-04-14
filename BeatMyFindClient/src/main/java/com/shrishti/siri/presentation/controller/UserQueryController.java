package com.shrishti.siri.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;
import com.shrishti.siri.service.UserQueryService;

@RestController
public class UserQueryController {
	
//	private final Log log = LogFactory.getLog(HomeController.class);  

	@Autowired
	private UserQueryService userQueryService;
	
	@RequestMapping("/fetchOpenQueries")
	public List<UserQuery> fetchOpenQueries(){
		try{
			return userQueryService.fetchOpenQueries();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/fetchClosedQueries")
	public List<UserQuery> fetchClosedQueries(){
		return userQueryService.fetchClosedQueries();
	}
	
	@RequestMapping("/fetchExpiredQueries")
	public List<UserQuery> fetchExpiredQueries(){
		return userQueryService.fetchExpiredQueries();
	}
	
	@RequestMapping("/fetchQueryDetails")
	public UserQuery fetchQueryDetails(@RequestParam(value="queryId") int queryId){
		System.out.println("QueryId in controller: "+queryId);
		return userQueryService.fetchQueryDetails(queryId);
	}
	
	@RequestMapping("/addUserQuery")
	public boolean fetchQueryDetails(@RequestBody UserQuery userQuery){
		System.out.println("Query in controller: "+userQuery.toString());
		try{
			userQueryService.addUserQuery(userQuery);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
