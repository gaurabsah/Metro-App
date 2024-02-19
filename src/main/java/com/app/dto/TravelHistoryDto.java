package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelHistoryDto {

	private String sourceLoc;

	private String destinationLoc;

	private double distance;

	private double amount;

}
