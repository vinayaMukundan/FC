package com.example.demo.booking.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.Passenger;


public class FlightBookingModel {
	
	private String id;
	
	@Valid
	@NotNull
	private Passenger passenger;
	
	
	private List<@Valid @NotNull Flight> flights ;
	
	public FlightBookingModel(String id, Passenger passenger, List<Flight> flights) {
		super();
		this.id = id;
		this.passenger = passenger;
		this.flights = flights;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public FlightBookingModel() {
		super();
	}

	@Override
	public String toString() {
		return "FlightBookingModel [id=" + id + ", passenger=" + passenger + ", flights=" + flights + "]";
	}
	

	
}
