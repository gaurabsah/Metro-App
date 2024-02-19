package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.MetroCardDto;
import com.app.dto.TravelHistoryDto;
import com.app.service.MetroCardService;
import com.app.utils.AppResponse;

@RestController
@RequestMapping("/api/v1/card")
public class MetroCardController {

	@Autowired
	private MetroCardService cardService;

	@PostMapping
	public ResponseEntity<MetroCardDto> saveDetails(@RequestBody MetroCardDto cardDto) {
		MetroCardDto details = cardService.saveDetails(cardDto);
		return new ResponseEntity<MetroCardDto>(details, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<MetroCardDto> updateDetails(@PathVariable String id, @RequestBody MetroCardDto cardDto) {
		MetroCardDto updateDetails = cardService.updateDetails(id, cardDto);
		return new ResponseEntity<MetroCardDto>(updateDetails, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<MetroCardDto>> getAllCards() {
		List<MetroCardDto> allDetails = cardService.getAllDetails();
		return new ResponseEntity<List<MetroCardDto>>(allDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MetroCardDto> getDetails(@PathVariable String id) {
		MetroCardDto cardDto = cardService.getDetails(id);
		return new ResponseEntity<MetroCardDto>(cardDto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDetails(@PathVariable String id) {
		cardService.deleteDetails(id);
		return new ResponseEntity<String>("Card Details Deleted Successfully...", HttpStatus.OK);
	}

	@GetMapping("/balanceEnquiry/{cardNumber}")
	public ResponseEntity<AppResponse> getBalance(@RequestParam String cardNumber) {
		AppResponse balanceEnquiry = cardService.balanceEnquiry(cardNumber);
		return new ResponseEntity<>(balanceEnquiry, HttpStatus.OK);
	}

	@GetMapping("/travel")
	public ResponseEntity<List<TravelHistoryDto>> travelHistory(@RequestParam String cardNum) {
		List<TravelHistoryDto> travelHistory = cardService.getTravelHistory(cardNum);
		return new ResponseEntity<>(travelHistory, HttpStatus.OK);
	}

	@GetMapping("/checkInOut")
	public ResponseEntity<Map<String, Object>> checkInOut(@RequestParam String cardNum, @RequestParam String sourceLoc,
			@RequestParam String destinationLoc) {
		Map<String, Object> checkInOut = cardService.checkInOut(cardNum, sourceLoc, destinationLoc);
		return new ResponseEntity<>(checkInOut, HttpStatus.OK);
	}
}
