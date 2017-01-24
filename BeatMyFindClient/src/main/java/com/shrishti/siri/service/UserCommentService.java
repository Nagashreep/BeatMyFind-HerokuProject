package com.shrishti.siri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shrishti.siri.dataAccessDelegate.dao.DataAccessDelegate;
import com.shrishti.siri.dataAccessDelegate.entity.UserComment;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;

@Service
public class UserCommentService {
	
	@Autowired
	private DataAccessDelegate dataAccessDelegate;
	
	public UserQuery addUserComment(UserComment userComment){
		return dataAccessDelegate.addUserComment(userComment);
	}

}
