package com.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FareResponse {
	
	private String message;

	private FareDto fareDto;

}
