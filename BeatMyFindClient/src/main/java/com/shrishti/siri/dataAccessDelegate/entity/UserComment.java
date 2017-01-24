package com.shrishti.siri.dataAccessDelegate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="USER_COMMENTS")
public class UserComment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Comment_Id")
	private int commentId;
	
	@Column(name="Comment_Text")
	private String commentText;
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private UserDetails user;
	
	@ManyToOne
	@JoinColumn(name="Query_Id")
	@JsonBackReference
	private UserQuery userQuery;
	
	@Column(name="Creation_Date")
	private Date creationDate;
	
	@Transient
	private int userQueryId;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	public UserQuery getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(UserQuery userQuery) {
		this.userQuery = userQuery;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}
	
	public int getUserQueryId() {
		return userQueryId;
	}

	public void setUserQueryId(int userQueryId) {
		this.userQueryId = userQueryId;
	}

	@Override
	public String toString(){
		return "CommentId: "+getCommentId()+" CommentText: "+getCommentText()+" User: "+getUser()+" UserQuery: "+getUserQuery()+" creationDate: "+getCreationDate()+" UserQueryId: "+getUserQueryId();
	}
}
