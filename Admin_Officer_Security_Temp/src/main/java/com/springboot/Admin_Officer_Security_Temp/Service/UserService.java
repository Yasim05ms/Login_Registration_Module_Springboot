package com.springboot.Admin_Officer_Security_Temp.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Admin_Officer_Security_Temp.Model.License;
import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	
	
	
	public void CreateUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public void updateuser(User user)
	{
		User existinguser=userRepository.getReferenceById(user.getUserId());
		String role=existinguser.getRole();
		if(!user.getUserName().equals(""))
		{
			existinguser.setUserName(user.getUserName());
		}
		if(!user.getPassword().equals(""))
		{
			existinguser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if(user.getRole()!=null && !user.getRole().equals("0"))
		{
			
			existinguser.setRole(user.getRole());
		}
		else {

			existinguser.setRole(role);
		}
		
		
		userRepository.save(existinguser);
	}
	
	public void updateprofile(User user)
	{
		User existinguser=userRepository.getReferenceById(user.getUserId());
		String role=existinguser.getRole();
		if(!user.getUserName().equals(""))
		{
			existinguser.setUserName(user.getUserName());
		}
		if(!user.getPassword().equals(""))
		{
			existinguser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		existinguser.setRole(role);
	}

	public String checkcheckLicenseStatus() {
		User admin=userRepository.getReferenceById(1);
		License license=admin.getLicense();
		if(license==null)
		{
			return null;
		}
		if(LocalDate.now().equals(license.getExpirydate()))
		{
			return "Expired";
		}
		
		return "Valid";
	}
	
	
}
