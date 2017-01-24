package com.shrishti.siri.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.shrishti.siri.dataAccessDelegate.dao.DataAccessDelegate;
import com.shrishti.siri.dataAccessDelegate.entity.UserDetails;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;

@Service
public class UserService {
	
	@Autowired
	private DataAccessDelegate dataAccessDelegate;
	
	public UserDetails saveUser(UserDetails user){
		user.setCreationDate(new Date());
		return dataAccessDelegate.saveUser(user);
	}
	
	public UserDetails authenticate(UserDetails user){
		return dataAccessDelegate.fetchUserDetails(user);
	}
	
	public boolean isUserNameUnique(String userName){
		return dataAccessDelegate.isUserNameUnique(userName);
	}
	public List<UserQuery> fetchUserQueries(int userId){
		return dataAccessDelegate.fetchUserQueries(userId);
	}
	
	public List<UserQuery> closeUserQueries(String[] queryIdsToClose){
		return dataAccessDelegate.closeUserQueries(queryIdsToClose);
	}

}
