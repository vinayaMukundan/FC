package com.example.demo.flightBooking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.booking.BookingService;
import com.example.demo.booking.BookingServiceImp;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;
import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.repo.FlightBookingRepository;
import com.example.demo.domain.repo.PassengerRepository;
import com.example.demo.passenger.PassengerService;
import com.example.demo.passenger.PassengerServiceTest;

@ExtendWith(SpringExtension.class)
public class FlightBookingServiceTest {
	@Mock
	FlightBookingRepository flightBookingRepo;

	@InjectMocks
	BookingService bookingService;

//	@InjectMocks
//	PassengerService passengerServiceTest;

	public FlightBookingServiceTest() {
		flightBookingRepo = mock(FlightBookingRepository.class);
		bookingService = new BookingServiceImp(flightBookingRepo);
	}

	@Test
	public void getFlightBookingById() {
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		flightSet.add(new Flight("FL-2", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya123@gmail.com");
		final FlightBooking flightBooking = new FlightBooking("FB-1", passenger, flightSet);

		Optional<FlightBooking> optional = Optional.of(flightBooking);
		Mockito.when(flightBookingRepo.findById("FB-1")).thenReturn(optional);
		assertThat(bookingService.getBooking("FB-1")).isNotNull();
		FlightBooking b = bookingService.getBooking(flightBooking.getId());
		assertEquals(flightBooking, b);
	}

	@Test
	public void testSaveNewFlightBooking() {
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		flightSet.add(new Flight("FL-2", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya123@gmail.com");
		FlightBooking flightBooking = new FlightBooking("FB-1", passenger, flightSet);
		bookingService.insertBooking(flightBooking);
		assertEquals("FB-1", flightBooking.getId());
		assertEquals(passenger, flightBooking.getPassenger());
		assertEquals(flightSet, flightBooking.getFlights());
	}

	@Test
	public void testUpdateFlightBookingDetails() {
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		flightSet.add(new Flight("FL-2", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));

		Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya123@gmail.com");
		FlightBooking flightBooking = new FlightBooking("FB-1", passenger, flightSet);
		bookingService.updateBooking(flightBooking);
		assertEquals("FB-1", flightBooking.getId());
		assertEquals(passenger, flightBooking.getPassenger());
		assertEquals(flightSet, flightBooking.getFlights());
	}

	@Test
	public void deleteFlightBookingById() {
		Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
		String message = bookingService.deleteBookingbyFlightid(flight.getId());
		verify(flightBookingRepo, times(1)).deleteById(flight.getId());
		assertEquals("Deleted..", message, message);
	}

	@Test
	public void getFlightBookingByPassengerId() {
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		flightSet.add(new Flight("FL-2", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya123@gmail.com");
		final FlightBooking flightBooking = new FlightBooking("FB-1", passenger, flightSet);

		List<FlightBooking> flightBookingList = new ArrayList<>();
		flightBookingList.add(flightBooking);
		// Optional<FlightBooking> optional = Optional.of(flightBooking);
		Mockito.when(flightBookingRepo.findByPassengerId("PS-1")).thenReturn(flightBookingList);

		assertThat(bookingService.getAllMultiFlightBookingsByPassenger("PS-1")).isNotNull();
		List<FlightBooking> fb = bookingService.getAllMultiFlightBookingsByPassenger("PS-1");
		assertEquals(flightBookingList, fb);
	}

	@Test
	public void findallBooking() {
		Set<Flight> flightSet = new HashSet<Flight>();
		flightSet.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		flightSet.add(new Flight("FL-2", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));

		Passenger passenger = new Passenger("PS-1", "Vinaya", "Mukundan", "vinaya123@gmail.com");
		FlightBooking flightBooking = new FlightBooking("FB-1", passenger, flightSet);

		FlightBooking flightBooking2 = new FlightBooking("FB-2", new Passenger("PS-2", "Swathi", "T", "swas@gmail.com"),
				flightSet);

		List<FlightBooking> airportList = new ArrayList<FlightBooking>();
		airportList.add(flightBooking);
		airportList.add(flightBooking2);
		Mockito.when(flightBookingRepo.findAll()).thenReturn(airportList);
		List<FlightBooking> list = bookingService.getAllMultiFlightBookings();
		assertEquals(2, list.size());

	}

}
