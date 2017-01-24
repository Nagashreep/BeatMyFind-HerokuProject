package com.shrishti.siri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shrishti.siri.dataAccessDelegate.dao.DataAccessDelegate;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;

@Service
public class UserQueryService {
	
	@Autowired
	private DataAccessDelegate dataAccessDelegate;
	
	public List<UserQuery> fetchOpenQueries(){
		List<UserQuery> userQueryList = dataAccessDelegate.fetchOpenQueries();
		/*QuerySummary querySummary = null;
		if(null!=userQueryList && !userQueryList.isEmpty()){
			querySummary = new QuerySummary();
			querySummary.setUserQueryList(userQueryList);
		}*/
		return userQueryList;
	}
	
	public List<UserQuery> fetchClosedQueries(){
		List<UserQuery> userQueryList = dataAccessDelegate.fetchClosedQueries();
		/*QuerySummary querySummary = null;
		if(null!=userQueryList && !userQueryList.isEmpty()){
			querySummary = new QuerySummary();
			querySummary.setUserQueryList(userQueryList);
		}*/
		return userQueryList;
	}
	
	public List<UserQuery> fetchExpiredQueries(){
		List<UserQuery> userQueryList = dataAccessDelegate.fetchExpiredQueries();
		/*QuerySummary querySummary = null;
		if(null!=userQueryList && !userQueryList.isEmpty()){
			querySummary = new QuerySummary();
			querySummary.setUserQueryList(userQueryList);
		}*/
		return userQueryList;
	}
	
	public UserQuery fetchQueryDetails(int queryId){
		return dataAccessDelegate.fetchQueryDetails(queryId);
	}
	
	public void addUserQuery(UserQuery userQuery){
		dataAccessDelegate.addUserQuery(userQuery);
	}

}
