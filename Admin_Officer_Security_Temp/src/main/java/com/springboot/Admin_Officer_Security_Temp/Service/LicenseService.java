package com.springboot.Admin_Officer_Security_Temp.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Admin_Officer_Security_Temp.Model.License;
import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.dao.LicenseRepository;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;


@Service
public class LicenseService {

	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private UserRepository userRepository;

	public String validateLicense(String licensekey) {
		License license= licenseRepository.findBylicensekey(licensekey);
		if(license!=null)
		{
			if(license.getExpirydate().equals(LocalDate.now())  || license.getExpirydate().isBefore(LocalDate.now()))
			{
				return "Expired";
			}
			
			else if(!license.getExpirydate().isBefore(LocalDate.now()))
			{
				User admin=userRepository.getReferenceById(1);
				 if (admin.getLicense() != null) {
	                    admin.setLicense(null);
	                    license.setUser(null);
	                    userRepository.save(admin);
		                licenseRepository.save(license);
	                }
	                admin.setLicense(license);
	                license.setUser(admin);
	                license.setStartdate(LocalDate.now());
	                userRepository.save(admin);
	                licenseRepository.save(license);
	                return "Validated";
			}
		}
		else {
			return null;
		}
		return "";
	}	
}
