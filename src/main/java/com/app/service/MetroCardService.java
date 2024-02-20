package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.dto.MetroCardDto;
import com.app.dto.PaymentRequest;
import com.app.dto.PaymentResponse;
import com.app.dto.TravelHistoryDto;
import com.app.model.Payment;
import com.app.utils.AppResponse;

public interface MetroCardService {

	MetroCardDto saveDetails(MetroCardDto cardDto);

	MetroCardDto updateDetails(String id, MetroCardDto cardDto);

	List<MetroCardDto> getAllDetails();

	MetroCardDto getDetails(String id);

	void deleteDetails(String id);

//	topUp
	PaymentResponse topUp(PaymentRequest paymentRequest);
	
//	view all transactions
	List<Payment> getAllTransactions();

//	view balance
	AppResponse balanceEnquiry(String cardNumber);

//	view travel history
	List<TravelHistoryDto> getTravelHistory(String cardNumber);

	/*
	 * scanning (check-in) scanning (check-out)
	 */

	Map<String, Object> checkInOut(String cardNumber, String sourceLoc, String destinationLoc);

}
