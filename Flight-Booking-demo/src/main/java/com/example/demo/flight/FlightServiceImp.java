package com.example.demo.flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.booking.BookingService;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.repo.AirportRepository;
import com.example.demo.domain.repo.FlightRepository;
import com.example.demo.domain.repo.PassengerRepository;
import com.example.demo.passenger.PassengerService;


@Service
@Transactional
public class FlightServiceImp implements FlightService{

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	public FlightServiceImp(FlightRepository flightRepo) {
		this.flightRepo = flightRepo;
	}
	
	@Override
	public String insertFlight(Flight flight) {
		String message;
		if(flight.getId()==null) {
			flightRepo.save(flight);
			message = "Flight details Added Successfully ...";
		}else {
			message = "Insertion Failed ...! Try Again ...!!";
		}
		return message;
	}
	
	@Override
	public String updateFlight(Flight flight) {
	//	getFlightById(flight.getId());
		flightRepo.save(flight);
		return "Flight Updated Successfully...!!";
	}
	
	@Override
	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}
	@Override
	public Flight getFlightById(String flightId) {
		return flightRepo.findById(flightId).orElseThrow(() -> new FlightNotFoundException(flightId));
	}
	@Override
	public String deleteFlight(String flightId) {
		//getFlightById(flightId);
		flightRepo.deleteById(flightId);
		return "Flight Deleted Successfully...!!";
	}
	@Override
	public List<Flight> findByDepartureAndDepartureDateGreaterThan(String departure, LocalDateTime departureDate) {
		return flightRepo.findByDepartureAndDepartureDateGreaterThan(departure, departureDate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*@Autowired
	private FlightService flightService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PassengerService passengerService;
*/
	
}
