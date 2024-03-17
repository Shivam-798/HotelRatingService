package com.hotel.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.service.entites.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repositories.HotelRepo;
import com.hotel.service.services.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepo hotelRepo;
	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		String id=UUID.randomUUID().toString();
		hotel.setHotelId(id);
		return this.hotelRepo.save(hotel);
	}

	public List<Hotel> allHotel() {
		// TODO Auto-generated method stub
		return this.hotelRepo.findAll();
	}

	@Override
	public Hotel get(String id) {
		// TODO Auto-generated method stub
		return this.hotelRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel is not found on server " +id));
	}

}
