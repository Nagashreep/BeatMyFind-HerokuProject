package com.shrishti.siri.dataAccessDelegate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="User_Details")
public class UserDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="User_Id")
	private int userId;
	
	@Column(name="User_Name")
	private String userName;
	
	@Column(name="User_Password")
	private String userPassword;
	
	@Column(name="First_Name")
	private String firstName;
	
	@Column(name="Middle_Name")
	private String middleName;
	
	@Column(name="Last_Name")
	private String lastName;
	
	@Column(name="Email_Address")
	private String emailAddress;
	
	@Column(name="Creation_Date")
	private Date creationDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString(){
		return "UserId: "+getUserId()+"UserName: "+getUserName()+" :FirstName: "+getFirstName()+" :MidName: "+" :LastName: "+getLastName()+" :Password: "+getUserPassword()+
				" :EmailAddress: "+getEmailAddress();
	}
}

