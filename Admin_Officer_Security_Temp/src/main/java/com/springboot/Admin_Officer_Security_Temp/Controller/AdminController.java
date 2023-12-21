package com.springboot.Admin_Officer_Security_Temp.Controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.Admin_Officer_Security_Temp.Model.License;
import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.Service.UserService;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;

@Controller
public class AdminController {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@GetMapping("/DashboardAdmin")
//	public String AdminHome(Model model)
//	{
//		List<User> users=userRepository.findAll();
//		model.addAttribute("users",users);
//		return "Admind";
//	}
	
	@GetMapping("/updateuser/{userId}")
	public String updateuser(@PathVariable ("userId") int userId,Model model)
	{
		User admin=userRepository.getReferenceById(1);
		License license=admin.getLicense();
		long remainingdays = ChronoUnit.DAYS.between(LocalDate.now(),license.getExpirydate());
		if(remainingdays==0)
		{
			model.addAttribute("remainingdays",false);
		}
		else if(remainingdays==1)
		{
			model.addAttribute("onedayremain", true);
		}
		else {
			
			model.addAttribute("remainingdays",remainingdays);
		}
		User user=userRepository.getReferenceById(userId);
		model.addAttribute("user", user);
		return "updateuser";
	}
	
	@PostMapping("/updateuser")
	public String Update(@ModelAttribute User user,RedirectAttributes redirectAttributes)
	{
		userService.updateuser(user);
		redirectAttributes.addFlashAttribute("updatesuccess", "User has been updated Successfully");
		return "redirect:/Allusers";
	}
	
	@GetMapping("/deleteuser/{userId}")
	public String deleteUser(@PathVariable ("userId") int userId,RedirectAttributes redirectAttributes)
	{
		User user=userRepository.getReferenceById(userId);
		userRepository.delete(user);
		redirectAttributes.addFlashAttribute("deletesuccess","User Deleted Successfully");
		return "redirect:/Allusers";
	}
}
