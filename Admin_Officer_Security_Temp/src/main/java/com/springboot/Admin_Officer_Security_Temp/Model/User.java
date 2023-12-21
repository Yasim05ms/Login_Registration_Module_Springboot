package com.springboot.Admin_Officer_Security_Temp.Model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;

	private String userName;
	
	@Column(unique = true)
	private String Email;
	 
	private String Password;
		
	private String Role;
	
	@OneToOne
	private License license;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User(int userId, String userName, String email, String password, String role, License license) {
		super();
		this.userId = userId;
		this.userName = userName;
		Email = email;
		Password = password;
		Role = role;
		this.license = license;
	}



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

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}
	
	
}
