package com.springboot.Admin_Officer_Security_Temp.Controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.Admin_Officer_Security_Temp.Model.License;
import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.Service.CustomUserdetails;
import com.springboot.Admin_Officer_Security_Temp.Service.LicenseService;
import com.springboot.Admin_Officer_Security_Temp.Service.UserService;
import com.springboot.Admin_Officer_Security_Temp.dao.LicenseRepository;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class MainController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private LicenseService licenseService;
	
	@GetMapping({"/","/login"})
	public String Login(@RequestParam(name = "error", required = false) String error,Model model)
	{
		String status=userService.checkcheckLicenseStatus();
		User admin=userRepository.getReferenceById(1);
		
		License license=admin.getLicense();
		if(status==null)
		{
			model.addAttribute("FirstLicenseKey","Please Enter the License Key to Activate the product");
			return "License";
		}
		else if(status.equals("Expired")){
			model.addAttribute("OldlicensekeyExpired","License Key Expired.Please Enter new License Key!");
			return "License";
		}
		
		if(error!=null)
		{
			model.addAttribute("error","Invalid Username or Password");
		}
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
		return "login";
	}
	
	
	@GetMapping("/Dashboard")
	public String Dashboard(Model model,HttpSession session)
	{
		String status=userService.checkcheckLicenseStatus();
		if(status==null)
		{
			model.addAttribute("FirstLicenseKey","Please Enter the License Key to Activate the product");
			return "License";
		}
		else if(status.equals("Expired")){
			model.addAttribute("OldlicensekeyExpired","License Key Expired.Please Enter new License Key!");
			return "License";
		}
		org.springframework.security.core.Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null && authentication.getPrincipal() instanceof CustomUserdetails)
		{
			CustomUserdetails userDetails = (CustomUserdetails) authentication.getPrincipal();
		    String username = userDetails.getUsername();
		User user=userRepository.findbyuserName(username);
		model.addAttribute("user",user);
		}
		List<User> users=userRepository.findAll();
		model.addAttribute("users", users);
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
		System.out.println(session.getId());
		return "Dashboard";
	}
	
	@GetMapping("/Adduser")
	public String Adduser(Model model)
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
		return "AddUser";
	}
	
	@PostMapping("/Adduser")
	public String Addnewuser(@ModelAttribute User user,Model model,RedirectAttributes redirectAttributes)
	{
		userService.CreateUser(user);
		redirectAttributes.addFlashAttribute("useraddsuccess","User Added Successfully");
		return "redirect:/Allusers";
	}
	
	@GetMapping("/Allusers")
	public String Allusers(Model model)
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
		List<User> users=userRepository.findAll();
		model.addAttribute("users",users);
		return "Alluser";
	}
	
	
	@GetMapping("/Updateprofile/{userId}")
	public String Updateprofile(@PathVariable ("userId") int userId,Model model,RedirectAttributes redirectAttributes)
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
		redirectAttributes.addFlashAttribute("useraddsuccess","User Added Successfully");
		return "updateself";
	}
	
	@PostMapping("/UpdateProfile")
	public String UpdateProfile(@ModelAttribute User user)
	{
		userService.updateprofile(user);
		return "redirect:/Myprofile";
	}
	
	@GetMapping("/Myprofile")
	public String Myprofile(Model model,Principal principal)
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
		String name=principal.getName();
		User user=userRepository.findbyuserName(name);
		model.addAttribute("user", user);
		return "profiles";
	}
	
	@GetMapping("/Officers")
	public String Officers(Model model)
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
		List<User> users=userRepository.finfbyRole("OFFICER");
		model.addAttribute("users", users);
		return "Officers";
	}
	
	@GetMapping("/Security")
	public String Security(Model model)
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
		List<User> users=userRepository.finfbyRole("SECURITY");
		model.addAttribute("users", users);
		return "Security";
	}
	
	@GetMapping("/usernameavailability")
	@ResponseBody
	public String usernameavailability(@RequestParam ("userName") String userName)
	{
		
		User user=userRepository.findbyuserName(userName);
		if(userName.length()>=3)
		{
			if(user!=null )
			{
				return "Username is not available";
			}
			else {
				return "Username is available";
			}
	}
		
		return "";
			
	}
	
	
	
	@GetMapping("/License")
	public String licensekey()
	{
		return "License";
	}
	
	@PostMapping("/LicenseValidate")
	public String LicenseValidate(@ModelAttribute License license,RedirectAttributes redirectAttributes)
	{
		String licensestatus=licenseService.validateLicense(license.getLicensekey());
		if(licensestatus==null)
		{
			redirectAttributes.addFlashAttribute("InvalidKey","Invalid Key ! Please Enter a Valid Key.");
			return "redirect:/License";
		}
		else if(licensestatus=="Expired")
		{
			redirectAttributes.addFlashAttribute("Expiredkey","Expired Key");
			return "redirect:/License";
		}
		else {
			
			return "redirect:/login";
		}
		
	}
	
}
