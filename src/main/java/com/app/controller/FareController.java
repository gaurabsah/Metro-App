package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FareDto;
import com.app.dto.FareResponse;
import com.app.service.FareService;

@RestController
@RequestMapping("/api/v1/fare")
public class FareController {

	@Autowired
	private FareService fareService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<FareDto> saveDetails(@RequestBody FareDto fareDto) {
		FareDto details = fareService.saveDetails(fareDto);
		return new ResponseEntity<>(details, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<FareDto> updateDetails(@PathVariable String id, @RequestBody FareDto fareDto) {
		FareDto updateDetails = fareService.updateDetails(id, fareDto);
		return new ResponseEntity<>(updateDetails, HttpStatus.OK);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<List<FareDto>> getAllCards() {
		List<FareDto> allDetails = fareService.getAllDetails();
		return new ResponseEntity<>(allDetails, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<FareDto> getDetails(@PathVariable String id) {
		FareDto dto = fareService.getDetails(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteDetails(@PathVariable String id) {
		fareService.deleteDetails(id);
		return new ResponseEntity<String>("Fare Details Deleted Successfully...", HttpStatus.OK);
	}

	@GetMapping("/fareEnquiry")
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<FareResponse> getBalance(@RequestParam String sourceLoc,
			@RequestParam String destinationLoc) {
		FareResponse showFare = fareService.showFare(sourceLoc, destinationLoc);
		return new ResponseEntity<>(showFare, HttpStatus.OK);
	}

}
