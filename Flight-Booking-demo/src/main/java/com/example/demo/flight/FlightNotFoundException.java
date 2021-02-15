package com.example.demo.flight;

import com.example.demo.exception.ResourceNotFoundException;

public class FlightNotFoundException extends ResourceNotFoundException{

	
	private static final long serialVersionUID = 6293499259584600291L;

	public FlightNotFoundException(String flightId) {
		super("Flight", "flight-id", flightId);
	}
	
}
