package com.shakthi.spring_boot_practice.Service;

import org.springframework.stereotype.Service;

import com.jvlcode.spring_boot_demo.exceptions.ResourceNotFoundException;
import com.shakthi.spring_boot_practice.Entity.UserAccessEntity;
import com.shakthi.spring_boot_practice.Repository.UseraccessRepo;

@Service
public class UserAccessService {

	private UseraccessRepo useracessrepo;
	
	public UserAccessEntity CreateUser(UserAccessEntity user) {
		return useracessrepo.save(user);
	}
	
	public UserAccessEntity getUserById(Long id) {
		return useracessrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not available with id: " + id));
	}
	
	
}
