package com.user.service.services;

import java.util.List;

import com.user.service.entites.User;

public interface User_Service {

	
	User saveUser(User user);
	
	
	List<User> getAllUser();
	
	User getUser(String userId);
	
	//delete
	
	//update
}
