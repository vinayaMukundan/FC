package com.example.demo.airport;

import com.example.demo.exception.ResourceNotFoundException;

public class AirportNotFoundException extends ResourceNotFoundException{

	private static final long serialVersionUID = -4185306016942664972L;

	public AirportNotFoundException(String airportId) {
		super("Airport", "airport-id", airportId);
	}
}
