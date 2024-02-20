package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {

	private double amount;

	private String type;

	private String cardHolderName;

	private String cardNumber;

	private int expiryYear;

	private int expiryMonth;

	private int cvc;

	private String metroCardNumber;

}
