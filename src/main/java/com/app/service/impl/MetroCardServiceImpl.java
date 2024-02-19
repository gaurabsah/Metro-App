package com.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.CheckInDto;
import com.app.dto.CheckOutDto;
import com.app.dto.MetroCardDto;
import com.app.dto.TravelHistoryDto;
import com.app.exception.ResourcesNotFoundException;
import com.app.model.Fare;
import com.app.model.MetroCard;
import com.app.model.TravelHistory;
import com.app.repository.FareRepository;
import com.app.repository.MetroCardRepository;
import com.app.repository.TravelHistoryRepository;
import com.app.service.MetroCardService;
import com.app.utils.AccountInfo;
import com.app.utils.AppConstants;
import com.app.utils.AppResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MetroCardServiceImpl implements MetroCardService {

	@Autowired
	private MetroCardRepository metroCardRepository;
	
	@Autowired
	private FareRepository fareRepository;
	
	
	@Autowired
	private TravelHistoryRepository travelHistoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MetroCardDto saveDetails(MetroCardDto cardDto) {
		log.info("Inside saveDetails...");
		MetroCard card = modelMapper.map(cardDto, MetroCard.class);
		card.setId(UUID.randomUUID().toString());
		card.setActive(true);
		card.setCardNumber(UUID.randomUUID().toString());
//		initial balance 0
		card.setBalance(0.0);
		log.info("Saving Metro Details...");
		MetroCard metroCard = metroCardRepository.save(card);
		return modelMapper.map(metroCard, MetroCardDto.class);
	}

	@Override
	public MetroCardDto updateDetails(String id, MetroCardDto cardDto) {
		log.info("Inside updateDetails()");
		log.info("fetching metrocard details having Id: " + id);
		MetroCard metroCard = metroCardRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Card with Id:" + id));
		metroCard.setActive(cardDto.isActive());
		metroCard.setBalance(cardDto.getBalance());
		metroCard.setCardHolderName(cardDto.getCardHolderName());
		metroCard.setCardNumber(cardDto.getCardNumber());
		log.info("Updating Metro card...");
		return modelMapper.map(metroCard, MetroCardDto.class);
	}

	@Override
	public List<MetroCardDto> getAllDetails() {
		log.info("Inside getAllDetails()");
		log.info("fetching All metrocard details");
		List<MetroCard> list = metroCardRepository.findAll(Sort.by("cardNumber"));
		List<MetroCardDto> cards = list.stream().map(m -> modelMapper.map(m, MetroCardDto.class))
				.collect(Collectors.toList());
		return cards;
	}

	@Override
	public MetroCardDto getDetails(String id) {
		log.info("Inside getDetails()");
		log.info("fetching metrocard details having Id: " + id);
		MetroCard metroCard = metroCardRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Card with Id:" + id));
		return modelMapper.map(metroCard, MetroCardDto.class);
	}

	@Override
	public void deleteDetails(String id) {
		log.info("Inside deleteDetails()");
		log.info("fetching metrocard details having Id: " + id);
		MetroCard metroCard = metroCardRepository.findById(id)
				.orElseThrow(() -> new ResourcesNotFoundException("Card with Id:" + id));
		log.info("Deleting metro card having Id: " + id);
		metroCardRepository.delete(metroCard);
	}

	@Override
	public AppResponse balanceEnquiry(String cardNum) {
		log.info("Inside balanceEnquiry()...");
		log.info("checking if card number is present in DB or not");
		Boolean cardNumber = metroCardRepository.existsByCardNumber(cardNum);
		if (!cardNumber) {
			log.info("If not present then...");
			return AppResponse.builder()
			.responseCode(AppConstants.ACCOUNT_NOT_EXIST_CODE)
			.responseMessage(AppConstants.ACCOUNT_NOT_EXIST_MESSAGE)
			.accountInfo(null)
			.build();

		}
		
		log.info("If present then...");
		log.info("Get the card number from DB...");
		MetroCard card = metroCardRepository.findByCardNumber(cardNum);
		return AppResponse.builder()
				.responseCode(AppConstants.ACCOUNT_EXISTS_CODE)
				.responseMessage(AppConstants.ACCOUNT_EXISTS_MESSAGE)
				.accountInfo(
						AccountInfo.builder()
						.accountBalance(card.getBalance())
						.accountName(card.getCardHolderName())
						.accountNumber(card.getCardNumber())
						.build()
						)
				.build();
	}

	@Override
	public Map<String, Object> checkInOut(String cardNumber, String sourceLoc, String destinationLoc) {
		
		log.info("Inside checkInOut()...");
		Map<String, Object> map = new HashMap<>();
		
		log.info("Finding the Card Details...");
		MetroCard card = metroCardRepository.findByCardNumber(cardNumber);
		
		if (card != null && !card.isCheckedIn()) {
			
//			check if destination location is present in DB
			
			Boolean existsBySourceLoc = fareRepository.existsBySourceLoc(sourceLoc);
			
			if(!existsBySourceLoc) {
				throw new ResourcesNotFoundException(sourceLoc + " Location Not Found!!!");
			}
			
//			log.info("Finding the fare details using location...");
//			Fare fare = fareRepository.findBySourceLoc(sourceLoc);
//			
//			
//			if(fare.getSourceLoc() != sourceLoc) {
//				throw new ResourcesNotFoundException(sourceLoc + " Location Not Found!!!");
//			}
			
			card.setCheckedIn(true);
			
			MetroCard metroCard = metroCardRepository.save(card);
			
			CheckInDto checkInDto = CheckInDto.builder()
			.sourceLocation(sourceLoc)
			.totalBalance(metroCard.getBalance())
			.build();
			
			map.put("check-in details", checkInDto);
			
		}
		
		else if (cardNumber != null && card.isCheckedIn()) {
			
//			check if destination location is present in DB
			
			Boolean existsByDestinationLoc = fareRepository.existsByDestinationLoc(destinationLoc);
			
			if(!existsByDestinationLoc) {
				throw new ResourcesNotFoundException(destinationLoc + " Location Not Found!!!");
			}
			
//			log.info("Finding the fare detail using location...");
//			Fare fareDetail = fareRepository.findByDestinationLoc(destinationLoc);
			
			
			log.info("Finding the fare amount using source and destination location...");
			Fare fareAmount = fareRepository.findBySourceLocAndDestinationLoc(sourceLoc, destinationLoc);
			
			card.setCheckedIn(false);
			
			
			CheckOutDto checkOutDto = CheckOutDto.builder()
			.sourceLocation(sourceLoc)
			.destinationLocation(destinationLoc)
			.deductedAmount(fareAmount.getAmount())
			.remainingBalance(card.getBalance() - fareAmount.getAmount())
			.build();
			
			
			card.setBalance(checkOutDto.getRemainingBalance());
			MetroCard metroCard = metroCardRepository.save(card);
			
//			saving travel location to travel history DB
			
			TravelHistory history = new TravelHistory();
			history.setAmount(checkOutDto.getDeductedAmount());
			history.setDestinationLoc(destinationLoc);
			history.setSourceLoc(sourceLoc);
			history.setDistance(fareAmount.getDistanceBetw());
			history.setMetroCard(metroCard);
			
			travelHistoryRepository.save(history);
			
			map.put("Check-Out Details", checkOutDto);
			
			
			 
		}
		
		
		return map;
	}

	@Override
	public List<TravelHistoryDto> getTravelHistory(String cardNumber) {
		List<TravelHistory> findByMetroCardCardNumber = travelHistoryRepository.findByMetroCardCardNumber(cardNumber);
		List<TravelHistoryDto> list = findByMetroCardCardNumber.stream().map(h->modelMapper.map(h, TravelHistoryDto.class)).collect(Collectors.toList());
		return list;
	}

}
