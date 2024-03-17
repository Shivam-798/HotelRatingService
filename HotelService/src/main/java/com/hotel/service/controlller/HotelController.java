package com.hotel.service.controlller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.service.entites.Hotel;
import com.hotel.service.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/createHotel")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
		Hotel htl=this.hotelService.create(hotel);
		return new ResponseEntity<Hotel>(htl,HttpStatus.OK);
		
	}
	@PreAuthorize("hasAuthority('SCOP_internal')")
	@GetMapping("/getHotel/{id}")
	public ResponseEntity<Hotel> getHotel(@PathVariable String id){
		Hotel h=this.hotelService.get(id);
		return new ResponseEntity<Hotel>(h,HttpStatus.OK);
		
	}
	
	@GetMapping("/getHotel")
	public ResponseEntity<List<Hotel>> getAllHotel(){
		List<Hotel> list=this.hotelService.allHotel();
		return new ResponseEntity<List<Hotel>>(list,HttpStatus.OK);
	}
}
