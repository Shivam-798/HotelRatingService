package com.hotel.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.service.entites.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
