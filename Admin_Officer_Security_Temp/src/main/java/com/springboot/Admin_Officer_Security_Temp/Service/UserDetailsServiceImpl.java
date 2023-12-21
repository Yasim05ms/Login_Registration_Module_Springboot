package com.springboot.Admin_Officer_Security_Temp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.Admin_Officer_Security_Temp.Model.User;
import com.springboot.Admin_Officer_Security_Temp.dao.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findbyuserName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserdetails(user);
	}

}
