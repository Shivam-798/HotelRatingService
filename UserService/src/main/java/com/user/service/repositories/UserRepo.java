package com.user.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.service.entites.User;

public interface UserRepo extends JpaRepository<User, String> {

	//if you want to implenets any custom method we write here
}
