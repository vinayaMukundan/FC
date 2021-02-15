package com.example.demo.booking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.example.demo.booking.models.FlightBookingModel;
import com.example.demo.domain.entity.Flight;
//import com.dxbair.services.flightbooking.booking.BookingNotFoundForPassengerException;
//import com.dxbair.services.flightbooking.domain.entity.Flight;
//import com.dxbair.services.flightbooking.domain.entity.Passenger;
import com.example.demo.domain.entity.FlightBooking;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.repo.FlightBookingRepository;
import com.example.demo.domain.repo.FlightRepository;
import com.example.demo.domain.repo.PassengerRepository;
import com.example.demo.flight.FlightService;
import com.example.demo.passenger.PassengerService;


@Service
@Transactional
public class BookingServiceImp implements BookingService{

	private static final Logger logger = LoggerFactory.getLogger(BookingServiceImp.class);

	@Autowired
	FlightBookingRepository bookingRepo;

	@PersistenceContext
    private EntityManager manager;
	
	@Autowired
	public BookingServiceImp(FlightBookingRepository bookingRepo) {
		this.bookingRepo = bookingRepo;
	}
	
	
	@Override
	public String insertBooking(FlightBooking model) {
		
		/*passengerService.getPassengerById(model.getId());
		Set<Flight> flightSet = model.getFlights();
		
		for(Flight flight : flightSet) {
			flightService.getFlightById(flight.getId());
		}*/
		bookingRepo.save(model);
		return "Added Successfully";
	}
	@Override
	public void updateBooking(FlightBooking model){
		//getBooking(model.getId());
		bookingRepo.save(model);
	}
	@Override
	public String deleteBookingbyFlightid(String bookingId) {
		bookingRepo.deleteById(bookingId);
		return "Deleted..";
	}
	@Override
	public FlightBooking getBooking(String bookingId) {
		return bookingRepo.findById(bookingId).orElseThrow(() -> new BookingNotFoundException(bookingId));
	}
	

	@Override
	public List<FlightBooking> getAllBookingsByPassenger(String passengerId) {
		List<FlightBooking> bookings = bookingRepo.findByPassengerId(passengerId);
		
		if (CollectionUtils.isEmpty(bookings))
			throw new BookingNotFoundForPassengerException(passengerId);
		return bookings;
	}
	
	@Override
	public List<FlightBooking> getAllMultiFlightBookingsByPassenger(String passengerId) {
		List<FlightBooking> bookings = bookingRepo.findByPassengerId(passengerId);
		logger.debug(">>>>>>>>>>>>>>>>>>>>>"+bookings);
		return bookings.stream().filter(booking -> booking.getFlights().size() > 1).collect(Collectors.toList());
	}
	
	@Override
	public List<FlightBooking> getAllMultiFlightBookings() {
		List<FlightBooking> bookings = bookingRepo.findAll();
		logger.debug(">>>>>>>>>>>>>>>>>>>>>"+bookings);
		return bookings;
	}
	
}
