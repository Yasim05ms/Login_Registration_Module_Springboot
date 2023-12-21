//package com.springboot.Admin_Officer_Security_Temp.Config;
//
//import java.io.IOException;
//import java.util.Set;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class CustomLoginSuccessRedirectHandler implements AuthenticationSuccessHandler {
//
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//			Authentication authentication) throws IOException, ServletException {
//		
//		Set<String> roles=AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//
//		if(roles.contains("ADMIN"))
//		{
//			response.sendRedirect("/DashboardAdmin");
//		}
//		else if(roles.contains("OFFICER"))
//		{
//			response.sendRedirect("/DashboardOfficer");
//		}
//		else {
//			response.sendRedirect("/DashboardSecurity");
//		}
//		
//	}
//
//}
