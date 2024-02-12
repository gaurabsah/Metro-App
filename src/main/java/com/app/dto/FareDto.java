package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FareDto {

	private String sourceLoc;

	private String destinationLoc;

	private double distanceBetw;

	private double amount;

}
