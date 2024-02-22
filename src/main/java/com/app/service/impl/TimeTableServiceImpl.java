package com.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.FareDto;
import com.app.model.Fare;
import com.app.repository.FareRepository;
import com.app.service.TimeTableService;

@Service
public class TimeTableServiceImpl implements TimeTableService {

	@Autowired
	private FareRepository fareRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public Map<String, List<FareDto>> getAllTimeTable() {
		Map<String, List<FareDto>> map = new HashMap<>();
		List<Fare> list = fareRepository.findAll();
		List<FareDto> dto = list.stream().map(t -> mapper.map(t, FareDto.class)).collect(Collectors.toList());
		map.put("Time Table", dto);
		return map;
	}

}
