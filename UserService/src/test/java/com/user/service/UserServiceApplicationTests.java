package com.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.service.entites.Rating;
import com.user.service.externalServices.RatingService;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingSevice;
	
	
//	void createRating() {
//		Rating rating=Rating.builder().rating(7).userId("1672e46b-9b84-4fda-8c59-5a1b65e18686").hotelId("34c6401c-9743-4416-b916-3e5e1f431fa6").feedback("this create feigin clint ").build();
//		
//	Rating savedRating=ratingSevice.createRating(rating);
//	System.out.println("crate datat "+savedRating);
//	}
}
