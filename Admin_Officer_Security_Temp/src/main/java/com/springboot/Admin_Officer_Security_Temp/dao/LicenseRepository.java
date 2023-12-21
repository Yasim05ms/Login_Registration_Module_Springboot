package com.springboot.Admin_Officer_Security_Temp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.Admin_Officer_Security_Temp.Model.License;

public interface LicenseRepository extends JpaRepository<License, Integer> {

	public License findBylicensekey(@RequestParam("licensekey") String licensekey);

}
