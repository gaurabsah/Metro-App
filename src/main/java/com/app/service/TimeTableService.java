package com.app.service;

import java.util.List;
import java.util.Map;

import com.app.dto.FareDto;

public interface TimeTableService {

// get all time

	Map<String, List<FareDto>> getAllTimeTable();

}
