package com.shakthi.spring_boot_practice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity

public class UserAccessEntity {

		@Id
		@GeneratedValue
		Long id;
		String email;
		String Password;
		
		
		
}
