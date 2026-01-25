package com.shakthi.spring_boot_practice.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shakthi.spring_boot_practice.Entity.UserAccessEntity;
import com.shakthi.spring_boot_practice.Repository.UseraccessRepo;
import com.shakthi.spring_boot_practice.Service.UserAccessService;
import com.shakthi.spring_boot_practice.Util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UseraccessRepo useraccessRepo;
	@Autowired
	private UserAccessService useraccessservice;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private JwtUtil jwtutil;
	
	 public AuthController(PasswordEncoder passwordencoder,UseraccessRepo useraccessRepo, UserAccessService useraccessservice) {
				this.passwordencoder = passwordencoder;
				this.useraccessRepo = useraccessRepo;
				this.useraccessservice = useraccessservice;
	 }
	 
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {

	    String email = body.get("email");
	    String password = body.get("password");

        // ✅ Correct encoding
        password = passwordencoder.encode(password);

	    if (useraccessRepo.findByEmail(email).isPresent()) {
	        return ResponseEntity.status(HttpStatus.CONFLICT)
	                .body("Email already exists");
	    }

	    UserAccessEntity user = new UserAccessEntity();
        user.setEmail(email);
        user.setPassword(password);

        useraccessservice.CreateUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Successfully registered");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {

	    String email = body.get("email");
	    String password = body.get("password");

	    var userOptional = useraccessRepo.findByEmail(email);
	    if(userOptional.isEmpty()) {
	    	return new ResponseEntity<>("user not fount", HttpStatus.NOT_FOUND);
	    }

	    UserAccessEntity user = userOptional.get();
	    if(!passwordencoder.matches(password, user.getPassword())) {
	    	return new ResponseEntity<>("invalid user , password doesnot match", HttpStatus.NOT_FOUND);
	    }
	    
	    String token= jwtutil.generateToken(email);
	    return ResponseEntity.ok(Map.of("token",token));
	}

	
	
}
