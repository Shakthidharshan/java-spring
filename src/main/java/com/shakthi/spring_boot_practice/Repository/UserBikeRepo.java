package com.shakthi.spring_boot_practice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shakthi.spring_boot_practice.Entity.bikeUserEntity;

public interface UserBikeRepo extends JpaRepository<bikeUserEntity, Long> {
}
