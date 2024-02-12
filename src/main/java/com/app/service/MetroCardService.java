package com.app.service;

import java.util.List;

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

//	scanning (check-out)

}
