package com.app.dto;

import lombok.Data;

@Data
public class MetroCardDto {

	private String cardHolderName;

	private String cardNumber;

	private double balance;

	private boolean isActive;
	
	private boolean checkedIn;

}
