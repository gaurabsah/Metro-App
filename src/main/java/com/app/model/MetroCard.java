package com.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	private boolean checkedIn;

//	private boolean checkOut = true;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "metroCard")
	private List<TravelHistory> travelHistories = new ArrayList<>();

}
