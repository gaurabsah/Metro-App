package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.TravelHistory;

public interface TravelHistoryRepository extends JpaRepository<TravelHistory, Long> {

	List<TravelHistory> findByMetroCardCardNumber(String cardNumber);

}
