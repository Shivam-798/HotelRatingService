package com.user.service.imple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entites.Hotel;
import com.user.service.entites.Rating;
import com.user.service.entites.User;
import com.user.service.excepions.ResourceNotFoundException;
import com.user.service.externalServices.HotelService;
import com.user.service.repositories.UserRepo;
import com.user.service.services.User_Service;



@Service
public class User_Service_Impl implements User_Service {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(User_Service_Impl.class);
	@Override
	public User saveUser(User user) {
	 
		String id=UUID.randomUUID().toString();
		user.setUserId(id);
		return this.userRepo.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		
		return this.userRepo.findAll();
	}


	
	@Override
	public User getUser(String userId) {
	
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with id given not found on server" +userId));
	
	   Rating[] forObject=restTemplate.getForObject("http://RATING-SERVICE/rating/byUser/"+user.getUserId(), Rating[].class);
		
		
		List<Rating>ratings=Arrays.stream(forObject).toList();
		System.out.println(ratings);
		List<Rating> ratingList=ratings.stream().map(rating->{
			
		//ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);


			Hotel hotel=this.hotelService.getHotel(rating.getHotelId());
		
		rating.setHotel(hotel);
		return rating;
		}).collect(Collectors.toList());
		
		
		user.setRatings(ratingList);
	return user;
	}

	
	
	//this method is restTemplate
//	@Override
//	public User getUser(String userId) {
//	
//		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with id given not found on server" +userId));
//	
//	   Rating[] forObject=restTemplate.getForObject("http://RATING-SERVICE/rating/byUser/"+user.getUserId(), Rating[].class);
//		
//		
//		List<Rating>ratings=Arrays.stream(forObject).toList();
//		System.out.println(ratings);
//		List<Rating> ratingList=ratings.stream().map(rating->{
//			
//		ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/getHotel/"+rating.getHotelId(), Hotel.class);
//		Hotel hotel=forEntity.getBody();
//		
//		rating.setHotel(hotel);
//		return rating;
//		}).collect(Collectors.toList());
//		
//		
//		user.setRatings(ratingList);
//	return user;
//	}

}
