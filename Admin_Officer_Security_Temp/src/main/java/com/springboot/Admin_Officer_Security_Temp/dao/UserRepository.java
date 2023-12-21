package com.springboot.Admin_Officer_Security_Temp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.Admin_Officer_Security_Temp.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("Select u from User u where u.userName=:userName")
	public User findbyuserName(@Param("userName") String userName);

	@Query("Select u from User u where u.Role=:Role")
	public List<User> finfbyRole(@Param("Role") String Role);

}
