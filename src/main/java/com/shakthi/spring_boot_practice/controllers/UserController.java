package com.shakthi.spring_boot_practice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shakthi.spring_boot_practice.Entity.bikeUserEntity;
import com.shakthi.spring_boot_practice.Repository.UserBikeRepo;


@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserBikeRepo  userRepo;
	@Autowired
	private bikeUserEntity userEntity;
	
	@GetMapping
	public List<bikeUserEntity> getuserbikeDetails() {
			return userRepo.findAll();
	}
	
	@GetMapping("/id/{id}")
	public bikeUserEntity getUserBikebyId(@PathVariable long id) {
		return userRepo.findById(id).orElseThrow();
	}
	
	 @GetMapping("/name/{name}")
    public ResponseEntity<bikeUserEntity> getUserBikeByName(@PathVariable String name) {
		 bikeUserEntity user = userRepo.findByName(name);
		    if (user == null) {
		        return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok(user);  
    }
	
	
}
