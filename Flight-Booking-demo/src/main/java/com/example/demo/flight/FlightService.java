package com.example.demo.flight;

import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.domain.entity.Flight;



public interface FlightService {

	Flight getFlightById(String flightId);
	List<Flight> getAllFlights();
	String insertFlight(Flight flight);
	String updateFlight(Flight flight);
	String deleteFlight(String flightId);
	List<Flight> findByDepartureAndDepartureDateGreaterThan(String departure, LocalDateTime departureDate);
}
