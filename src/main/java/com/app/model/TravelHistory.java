package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_history")
public class TravelHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String sourceLoc;

	private String destinationLoc;

	private double distance;

	private double amount;

	@ManyToOne
	@JoinColumn(name = "card_id", referencedColumnName = "id")
	private MetroCard metroCard;

}
