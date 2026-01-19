package com.shakthi.spring_boot_practice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shakthi.spring_boot_practice.Entity.bikeUserEntity;

public interface UserBikeRepo extends JpaRepository<bikeUserEntity, Long> {


    bikeUserEntity findByName(String name);

    List<bikeUserEntity> findAllByName(String name);
}
