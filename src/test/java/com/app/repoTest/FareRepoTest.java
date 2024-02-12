package com.app.repoTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.model.Fare;
import com.app.repository.FareRepository;

@SpringBootTest
public class FareRepoTest {

	@Autowired
	private FareRepository fareRepository;

	@Test
	void showFareTest() {
		Fare findBySourceLocAndDestinationLoc = fareRepository.findBySourceLocAndDestinationLoc("Kolhabi", "Nijgadh");

		System.out.println(findBySourceLocAndDestinationLoc.getAmount());
	}

}
