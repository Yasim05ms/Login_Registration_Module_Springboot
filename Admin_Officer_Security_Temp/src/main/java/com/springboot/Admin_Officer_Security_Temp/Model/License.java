package com.springboot.Admin_Officer_Security_Temp.Model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class License implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int license_Id;
	
	private String licensekey;
	
	private LocalDate Startdate;
	
	private LocalDate expirydate;
	
	@OneToOne
	private User user;

	public License() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public License(int license_Id, String licensekey, LocalDate startdate, LocalDate expirydate, User user) {
		super();
		this.license_Id = license_Id;
		this.licensekey = licensekey;
		Startdate = startdate;
		this.expirydate = expirydate;
		this.user = user;
	}



	public int getLicense_Id() {
		return license_Id;
	}

	public void setLicense_Id(int license_Id) {
		this.license_Id = license_Id;
	}

	public String getLicensekey() {
		return licensekey;
	}

	public void setLicensekey(String licensekey) {
		this.licensekey = licensekey;
	}

	public LocalDate getStartdate() {
		return Startdate;
	}

	public void setStartdate(LocalDate startdate) {
		Startdate = startdate;
	}

	public LocalDate getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(LocalDate expirydate) {
		this.expirydate = expirydate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
