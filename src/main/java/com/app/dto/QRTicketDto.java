package com.app.dto;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QRTicketDto {

	private String sourceLoc;

	private String destinationLoc;

	private int quantity;

	@CreationTimestamp
	private LocalDateTime creationDate;

	private LocalDateTime validityDays = LocalDateTime.now().plusDays(1L);

}
