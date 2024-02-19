package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.MetroCard;

public interface MetroCardRepository extends JpaRepository<MetroCard, String> {

	MetroCard findByCardNumber(String cardNumber);

	Boolean existsByCardNumber(String cardNumber);

//	List<MetroCard> findByCheckIn(boolean checkIn);

}
