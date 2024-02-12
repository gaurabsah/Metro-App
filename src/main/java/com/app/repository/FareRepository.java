package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Fare;

public interface FareRepository extends JpaRepository<Fare, String> {

	Boolean existsBySourceLoc(String sourceLoc);

	List<Fare> findBySourceLoc(String sourceLoc);

	Boolean existsByDestinationLoc(String destinationLoc);

	List<Fare> findByDestinationLoc(String destinationLoc);

	Fare findBySourceLocAndDestinationLoc(String sourceLoc, String destinationLoc);
	
	

}
