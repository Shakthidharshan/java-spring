package com.shakthi.spring_boot_practice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shakthi.spring_boot_practice.Entity.UserAccessEntity;
@Repository

public interface UseraccessRepo extends JpaRepository<UserAccessEntity, Long>  {

	Optional<UserAccessEntity> findByEmail(String email);
	
}

