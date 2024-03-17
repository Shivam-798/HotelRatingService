package com.hotel.service.services;

import com.hotel.service.entites.Hotel;
import java.util.*;

public interface HotelService {

	Hotel create(Hotel hotel);
	
	List<Hotel> allHotel();
	
	Hotel get(String id);
}
