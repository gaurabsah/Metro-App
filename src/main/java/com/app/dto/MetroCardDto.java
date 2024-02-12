package com.app.dto;

import lombok.Data;

@Data
public class MetroCardDto {

	private String cardHolderName;

	private String cardNumber;

	private String balance;

	private boolean isActive;

}
