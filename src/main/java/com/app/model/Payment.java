package com.app.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double amount;

	private String type;

	private String cardHolderName;

	private String cardNumber;

	private int expiryYear;

	private int expiryMonth;

	private int cvc;

	@CreationTimestamp
	private LocalDateTime paidOn;

	private String metroCardNumber;
}