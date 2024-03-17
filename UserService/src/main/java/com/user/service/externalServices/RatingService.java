package com.user.service.externalServices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.user.service.entites.Rating;

@FeignClient(name="RATING-SERVICE")
@Service
public interface RatingService {

	//get
	
	//post
	@PostMapping("/rating/createRating")
	Rating createRating(Rating rating);
}
