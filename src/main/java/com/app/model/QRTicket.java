package com.app.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "qr_tickets")
public class QRTicket {

	@Id
	private String id;

	private String sourceLoc;

	private String destinationLoc;

	private int quantity;

	@CreationTimestamp
	private LocalDateTime creationDate;

	private LocalDateTime validityDays;

}
