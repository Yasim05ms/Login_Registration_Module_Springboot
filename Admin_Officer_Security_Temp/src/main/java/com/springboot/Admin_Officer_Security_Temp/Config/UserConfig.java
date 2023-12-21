package com.springboot.Admin_Officer_Security_Temp.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import com.springboot.Admin_Officer_Security_Temp.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class UserConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	 {
		http.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests((authorize)->
		authorize.requestMatchers("/Adduser","/Allusers","/Officers","/Security").hasAuthority("ADMIN")
		.requestMatchers("/Dashboard","/Myprofile","/Updateprofile/**").authenticated()
		.requestMatchers("/**","/License").permitAll()
		)
		.formLogin(
				form->
		form.loginPage("/login").permitAll()
		.defaultSuccessUrl("/Dashboard")
//		.successHandler(sucessHandler)
		.permitAll()
		);
		return http.build();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean
	public SessionAuthenticationStrategy sessionAuthenticationStrategy() {
		return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry());
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}
}
