package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {

}
