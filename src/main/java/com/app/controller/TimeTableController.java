package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FareDto;
import com.app.service.TimeTableService;

@RestController
@RequestMapping("/api/v1/time")
public class TimeTableController {

	@Autowired
	private TimeTableService timeTableService;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	public ResponseEntity<Map<String, List<FareDto>>> getAll() {
		Map<String, List<FareDto>> allTimeTable = timeTableService.getAllTimeTable();
		return new ResponseEntity<>(allTimeTable, HttpStatus.OK);
	}

}
