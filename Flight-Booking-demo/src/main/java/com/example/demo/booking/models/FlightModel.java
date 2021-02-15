package com.example.demo.booking.models;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

public class FlightModel {
	
	@NotNull
	@NotBlank(message = "departure is mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String departure;
	@NotNull
	@NotBlank(message = "arrival is mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String arrival;
	@NotNull
	private LocalDateTime departureDate;
	@NotNull
	private LocalDateTime arrivalDate;
	public FlightModel(String departure, String arrival, LocalDateTime departureDate, LocalDateTime arrivalDate) {
		super();
		this.departure = departure;
		this.arrival = arrival;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}
	public FlightModel() {
		super();
	}
	
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
	public LocalDateTime getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	@Override
	public String toString() {
		return "FlightModel [departure=" + departure + ", arrival=" + arrival + ", departureDate="
				+ departureDate + ", arrivalDate=" + arrivalDate + "]";
	}
}
