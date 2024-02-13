package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.CheckOut;

public interface CheckOutRepository extends JpaRepository<CheckOut, Long> {

}
