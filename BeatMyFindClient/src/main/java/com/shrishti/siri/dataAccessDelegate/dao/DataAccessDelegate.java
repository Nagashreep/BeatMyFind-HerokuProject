package com.shrishti.siri.dataAccessDelegate.dao;

import java.util.List;

import com.shrishti.siri.dataAccessDelegate.entity.UserComment;
import com.shrishti.siri.dataAccessDelegate.entity.UserDetails;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;

public interface DataAccessDelegate {
	
	public UserDetails fetchUserDetails(UserDetails user);
	
	public UserDetails saveUser(UserDetails user);
	
	public List<UserQuery> fetchOpenQueries();
	
	public List<UserQuery> fetchClosedQueries();
	
	public List<UserQuery> fetchExpiredQueries();
	
	public UserQuery fetchQueryDetails(int queryId);
	
	public UserQuery addUserComment(UserComment userComment);
	
	public void addUserQuery(UserQuery userQuery);
	
	public boolean isUserNameUnique(String userName);
	
	public List<UserQuery> fetchUserQueries(int userId);
	
	public List<UserQuery> closeUserQueries(String[] queryIdsToClose);

}
