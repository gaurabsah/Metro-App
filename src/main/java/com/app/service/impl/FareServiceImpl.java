package com.app.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.FareDto;
import com.app.dto.FareResponse;
import com.app.exception.ResourcesNotFoundException;
import com.app.model.Fare;
import com.app.repository.FareRepository;
import com.app.service.FareService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FareServiceImpl implements FareService {

	@Autowired
	private FareRepository fareRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public FareDto saveDetails(FareDto fareRequest) {
		log.info("Inside saveDetails()...");
		Fare fare = mapper.map(fareRequest, Fare.class);
		fare.setId(UUID.randomUUID().toString());
		fare.setAmount(fare.getDistanceBetw() * 5.0);
		log.info("Saving Data...");
		Fare newFare = fareRepository.save(fare);
		return mapper.map(newFare, FareDto.class);
	}

	@Override
	public FareDto updateDetails(String id, FareDto fareRequest) {
		log.info("Inside updateDetails()...");
		Fare fare = fareRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Detail not found with id: " + id));
		fare.setAmount(fareRequest.getAmount());
		fare.setDestinationLoc(fareRequest.getDestinationLoc());
		fare.setSourceLoc(fareRequest.getSourceLoc());
		log.info("Updating Data...");
		Fare updatedFare = fareRepository.save(fare);
		return mapper.map(updatedFare, FareDto.class);
	}

	@Override
	public List<FareDto> getAllDetails() {
		log.info("Inside getAllDetails()...");
		log.info("Fetching All Data...");
		List<Fare> list = fareRepository.findAll(Sort.by("sourceLoc"));
		return list.stream().map(l -> mapper.map(l, FareDto.class)).collect(Collectors.toList());
	}

	@Override
	public FareDto getDetails(String id) {
		log.info("Inside getDetails()...");
		log.info("Fetching Data...");
		Fare fare = fareRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Detail not found with id: " + id));
		return mapper.map(fare, FareDto.class);
	}

	@Override
	public void deleteDetails(String id) {
		log.info("Inside deleteDetails()...");
		log.info("Fetching Data...");
		Fare fare = fareRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Detail not found with id: " + id));
		log.info("Deleting Data...");
		fareRepository.delete(fare);

	}

	@Override
	public FareResponse showFare(String source, String destination) {
		log.info("Inside showFare()...");
		log.info("checking if Source Location is present in DB or not");
		Boolean sourceLoc = fareRepository.existsBySourceLoc(source);
		if(!sourceLoc) {
			return FareResponse.builder()
					.message("Source Location Not Found...")
					.fareDto(
							FareDto.builder()
							.amount(0.0)
							.destinationLoc(destination)
							.sourceLoc(source)
							.distanceBetw(0.0)
							.build()
							)
					.build();
		}
		
		log.info("If present then...");
		log.info("Get the source Location from DB...");
		List<Fare> sourLoc = fareRepository.findBySourceLoc(source);
		
		log.info("checking if Destination Location is present in DB or not");
		Boolean destinationLoc = fareRepository.existsByDestinationLoc(destination);
		if(!destinationLoc) {
			return FareResponse.builder()
					.message("Destination Location Not Found...")
					.fareDto(
							FareDto.builder()
							.amount(0.0)
							.destinationLoc(destination)
							.sourceLoc(source)
							.distanceBetw(0.0)
							.build()
							)
					.build();
		}
		
		if(!sourceLoc || !destinationLoc) {
			throw new ResourcesNotFoundException("Location Not Present in Database...");
		}
		
		
		
		log.info("If present then...");
		log.info("Get the source Location from DB...");
		List<Fare> desLoc = fareRepository.findByDestinationLoc(destination);
		
		log.info("Get Fare Detail using Source & Destination Location For Fare amount...");
		Fare fare = fareRepository.findBySourceLocAndDestinationLoc(source, destination);
		
		return FareResponse.builder()
				.message("SUCCESS...")
				.fareDto(
						FareDto.builder()
						.amount(fare.getAmount())
						.destinationLoc(destination)
						.sourceLoc(source)
						.distanceBetw(fare.getDistanceBetw())
						.build()
						)
				.build();
	}

}
