package com.shrishti.siri.presentation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONPObject;
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
	public Map<String, String> fetchQueryDetails(@RequestBody UserQuery userQuery){
		System.out.println("Query in controller: "+userQuery.toString());
		Map<String, String> resultantMap = new HashMap<String, String>();
		try{
			userQueryService.addUserQuery(userQuery);
			resultantMap.put("Result", "Success");
			return resultantMap;
		}catch(Exception e){
			e.printStackTrace();
			resultantMap.put("Result", "Error");
			return resultantMap;
		}
	}

}
