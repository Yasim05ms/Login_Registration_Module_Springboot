package com.springboot.Admin_Officer_Security_Temp.Config;

import java.time.LocalDate;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.springboot.Admin_Officer_Security_Temp.Model.License;
import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.dao.LicenseRepository;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;


@Component
public class InitialDataloader implements ApplicationListener<ContextRefreshedEvent> {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final LicenseRepository licenseRepository;
	
	
	
	public InitialDataloader(UserRepository userRepository, PasswordEncoder passwordEncoder, LicenseRepository licenseRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.licenseRepository = licenseRepository;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createDeafaultAdminUser();
		}

	private void createDeafaultAdminUser() {
		
		User admin=userRepository.findbyuserName("admin");
	
		if(admin==null)
		{
			admin=new User();
			admin.setUserName("admin");
			admin.setPassword(passwordEncoder.encode("admin"));
			admin.setRole("ADMIN");
			userRepository.save(admin);
			
		}
		
	}
	
}
