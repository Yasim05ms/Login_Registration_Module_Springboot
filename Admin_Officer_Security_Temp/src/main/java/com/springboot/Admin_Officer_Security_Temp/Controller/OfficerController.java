package com.springboot.Admin_Officer_Security_Temp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;

@Controller
public class OfficerController {

	@Autowired
	private UserRepository userRepository;
	
//	@GetMapping("/DashboardOfficer")
//	public String OfficerDashboard()
//	{
//		return "officerd";
//	}
}
