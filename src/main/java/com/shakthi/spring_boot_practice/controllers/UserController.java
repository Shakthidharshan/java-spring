package com.shakthi.spring_boot_practice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jvlcode.spring_boot_demo.exceptions.ResourceNotFoundException;
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
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not available with id: " + id));
	}
	
	@GetMapping("/name/{name}")
    public ResponseEntity<bikeUserEntity> getUserBikeByName(@PathVariable String name) {
		 bikeUserEntity user = userRepo.findByName(name);
		    if (user == null) {
		        return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok(user);  
    }
    
    @PutMapping("/{id}")
    public bikeUserEntity updateuserdetails(@PathVariable long id , @RequestBody bikeUserEntity users ) {
		    bikeUserEntity userdata = userRepo.findById(id).orElseThrow();
		    userdata.setName(users.getName());
		    userdata.setAddress(users.getAddress());
		    userdata.setEngineType(users.getEngineType());
		    userdata.setPrice(users.getPrice());
		    userdata.setType(users.getType());
		    userdata.setCc(users.getCc());
		    return userRepo.save(userdata);
    }
    
    @PostMapping
    public bikeUserEntity createUserDetails(@RequestBody bikeUserEntity users) {
    	return userRepo.save(users);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserDetails(@PathVariable long id) {
    	bikeUserEntity userdata = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not available with id: " + id));
    	userRepo.delete(userdata);
    	return ResponseEntity.ok().build();
    }
    
   
	
	
}
