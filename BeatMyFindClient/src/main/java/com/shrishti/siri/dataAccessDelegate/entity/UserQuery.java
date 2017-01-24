package com.shrishti.siri.dataAccessDelegate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="User_Query")
public class UserQuery implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="Query_Id")
	private int queryId;
	
	@Column(name="Query_Text")
	private String queryText;
	
	@Column(name="Query_Status")
	private String queryStatus;
	
	@ManyToOne
	@JoinColumn(name="User_Id")
	private UserDetails user;
	
	@Column(name="Creation_Date")
	private Date creationDate;
	
	@OneToMany(mappedBy = "userQuery", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JsonManagedReference
	@OrderBy("creationDate desc")
	private List<UserComment> userCommentsList;
	
	@Transient
	private boolean hasMoreComments = false;

	public boolean isHasMoreComments() {
		return hasMoreComments;
	}

	public void setHasMoreComments(boolean hasMoreComments) {
		this.hasMoreComments = hasMoreComments;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<UserComment> getUserCommentsList() {
		return userCommentsList;
	}

	public void setUserCommentsList(List<UserComment> userCommentsList) {
		this.userCommentsList = userCommentsList;
	}
	
	@Override
	public String toString(){
		return "queryId: "+getQueryId()+" QueryStatus: "+getQueryStatus()+" queryText: "+getQueryText()+" CreationDate: "+getCreationDate();
	}
}
