package com.app.service;

import java.util.List;

import com.app.dto.CheckInDto;
import com.app.dto.CheckOutDto;
import com.app.dto.MetroCardDto;
import com.app.utils.AppResponse;

public interface MetroCardService {

	MetroCardDto saveDetails(MetroCardDto cardDto);

	MetroCardDto updateDetails(String id, MetroCardDto cardDto);

	List<MetroCardDto> getAllDetails();

	MetroCardDto getDetails(String id);

	void deleteDetails(String id);

//	topup

//	view balance
	AppResponse balanceEnquiry(String cardNumber);

//	view travel history

//	scanning (check-in)
	CheckInDto checkIn(String cardNum, String sourceLoc);

//	scanning (check-out)
	CheckOutDto checkOut(String cardNum, String sourceLoc, String destinationLoc);

}
