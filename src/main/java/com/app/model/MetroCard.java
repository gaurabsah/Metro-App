package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "card")
public class MetroCard {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String cardHolderName;
	
	private String cardNumber;
	
	private double balance;
	
	private boolean isActive;

}
