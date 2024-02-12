package com.app.service;

import java.util.List;

import com.app.dto.FareDto;
import com.app.dto.FareResponse;

public interface FareService {

	FareDto saveDetails(FareDto fareDto);

	FareDto updateDetails(String id, FareDto fareDto);

	List<FareDto> getAllDetails();

	FareDto getDetails(String id);

	void deleteDetails(String id);

//	show fares
	FareResponse showFare(String sourceLoc, String destinationLoc);

}
