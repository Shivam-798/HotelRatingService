package com.user.service.controller;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.services.User_Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

import com.user.service.entites.*;
import com.user.service.imple.User_Service_Impl;
@RestController
@RequestMapping("/user")
public class User_Controller {
	
	@Autowired
	private User_Service user_Service;
	
	private Logger logger=LoggerFactory.getLogger(User_Service_Impl.class);

	
	@PostMapping("/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User u=this.user_Service.saveUser(user);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}
	int retryCount=1;
	
	@GetMapping("/getUser/{userId}")
	//@CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
	//@Retry(name="ratingHotelService", fallbackMethod="ratingHotelFallback")
	@RateLimiter(name="userRAteLimiter",fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUser(@PathVariable String userId){
		logger.info("Retry count: {}",retryCount);
		retryCount++;
		User u=this.user_Service.getUser(userId);
		return new ResponseEntity<User>(u,HttpStatus.OK);
	}

	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){
		//logger.info("fallback is executed", ex.getMessage());
		ex.printStackTrace();
		User user=	User.builder().email("dummy@gmail.com")
			.name("Dummy")
			.about("This user is created dummy beacuse some service is down")
			.userId("1234")
			.build();
			return new ResponseEntity<User>(user,HttpStatus.OK );
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<List<User>> allUser(){
		List<User> list=this.user_Service.getAllUser();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}
}
