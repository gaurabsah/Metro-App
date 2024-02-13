package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOutDto {

	private String sourceLocation;

	private String destinationLocation;
	
	private double deductedAmount;

	private double remainingBalance;

}
