package com.example.demo.airport;

import java.util.List;
import com.example.demo.domain.entity.Airport;



public interface AirportService {

	Airport getAirportById(String airportId);
	List<Airport> getAllAirports();
	String insertAirport(Airport airport);
	String updateAirport(Airport airport);
	String deleteAirport(String airportId);
}
