package com.example.demo.passenger;

import java.util.List;

import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.entity.Passenger;



public interface PassengerService {

	Passenger getPassengerById(String passengerId);
	List<Passenger> getAllPassengers();
	
	String insertPassenger(Passenger passenger);
	String updatePassenger(Passenger passenger);
	String deletePassenger(String passengerId);
	
	Passenger findByEmail(String email);
	
}
