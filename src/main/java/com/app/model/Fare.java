package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "fare_info")
public class Fare {

	@Id
	private String id;

	private String sourceLoc;

	private String destinationLoc;

	private double distanceBetw;

	private double amount;

	private String timeCovered;

}
