package com.shrishti.siri.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shrishti.siri.dataAccessDelegate.entity.UserComment;
import com.shrishti.siri.dataAccessDelegate.entity.UserQuery;
import com.shrishti.siri.service.UserCommentService;

@RestController
public class UserCommentsController {
	
	@Autowired
	private UserCommentService userCommentService;
	
	@RequestMapping(value="/addUserComment", method=RequestMethod.POST)
	public UserQuery addUserComment(@RequestBody UserComment userComment){
		System.out.println("UserComment inside CommentsCOntroller: "+userComment.toString());
		return userCommentService.addUserComment(userComment);
	}

}
