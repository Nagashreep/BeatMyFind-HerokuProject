package com.shrishti.siri.dataAccessDelegate.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Editorial")
public class Editorial {
	
	@Id
	@GeneratedValue
	@Column(name="EDITORIAL_ID")
	public Integer editorialId;
	
	@Column(name="HEADING_TEXT")
	public String headingText;
	
	@Column(name="MAIN_CONTENT")
	@Lob
	public String mainContent;
	
	@Column(name="editorial_date")
	public Date editorialDate;
	
	
//	mappedBy = "userQuery", 
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	public UserDetails user;
	
	
	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Integer getEditorialId() {
		return editorialId;
	}
	
	public void setEditorialId(Integer editorialId) {
		this.editorialId = editorialId;
	}
	public Date getEditorialDate() {
		return editorialDate;
	}
	public void setEditorialDate(Date editorialDate) {
		this.editorialDate = editorialDate;
	}
	public String getHeadingText() {
		return headingText;
	}
	public void setHeadingText(String headingText) {
		this.headingText = headingText;
	}
	public String getMainContent() {
		return mainContent;
	}
	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

}
