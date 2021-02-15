package com.example.demo.booking;

import java.util.List;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;



public interface BookingService {
	
	
	FlightBooking getBooking(String bookingId);
	List<FlightBooking> getAllBookingsByPassenger(String passengerId);
	List<FlightBooking> getAllMultiFlightBookingsByPassenger(String passengerId);
	List<FlightBooking> getAllMultiFlightBookings();
	
	
	
	
	//void createSampleBookings();
	void updateBooking(FlightBooking model);
	String insertBooking(FlightBooking model);
	String deleteBookingbyFlightid(String flightId);

}
