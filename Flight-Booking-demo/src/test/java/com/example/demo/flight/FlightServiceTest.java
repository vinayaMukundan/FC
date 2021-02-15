package com.example.demo.flight;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.repo.FlightRepository;

@ExtendWith(SpringExtension.class)
public class FlightServiceTest {
	
	@Mock
	FlightRepository flightRepository;
	
	@InjectMocks
	FlightService flightService;

	public FlightServiceTest() {
		flightRepository = mock(FlightRepository.class);
		flightService = new FlightServiceImp(flightRepository);
	}
	
	@Test
    public void findFilghtByDeparture() {
		Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
		List<Flight> flightList = flightService.findByDepartureAndDepartureDateGreaterThan("KOC", LocalDateTime.now());
		verify(flightRepository,times(1)).findByDepartureAndDepartureDateGreaterThan(flight.getDeparture(), flight.getDepartureDate());
		assertEquals(0, flightList.size());
	}
	 
	@Test
    public void deleteFlightById()
    {
		Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
		String message = flightService.deleteFlight(flight.getId());
		verify(flightRepository,times(1)).deleteById(flight.getId());
		assertEquals("Flight Deleted Successfully...!!",
				"Flight Deleted Successfully...!!", message);
    }
	 @Test
	 public void testSaveNewFlight() {
	     Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
	     Mockito.when(flightRepository.save(flight)).thenReturn(flight);
	     String message = flightService.insertFlight(flight);
	     assertEquals("FL-1", flight.getId());
	     assertEquals("TVM", flight.getArrival());
	     assertEquals("KOC", flight.getDeparture());
	     assertEquals("Flight details Added Successfully ...",message,message);
	  }
	
	 @Test
	 public void testUpdateNewFlight() {
	     Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
	     Mockito.when(flightRepository.save(flight)).thenReturn(flight);
	     String message = flightService.updateFlight(flight);
	     assertEquals("FL-1", flight.getId());
	     assertEquals("TVM", flight.getArrival());
	     assertEquals("KOC", flight.getDeparture());
	     assertEquals("Flight Updated Successfully...!!",
	    		 "Flight Updated Successfully...!!", message);
	  }
	 
	 @Test
	 public void testFindallFlight() {
		 List<Flight> flightList = new ArrayList<Flight>();
		 flightList.add(new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now()));
		 flightList.add(new Flight("FL-2", "BANG", "TVM", LocalDateTime.now(), LocalDateTime.now()));

		 Mockito.when(flightRepository.findAll()).thenReturn(flightList);
		 List<Flight> flightListExpected = flightService.getAllFlights();
		 assertEquals(flightListExpected, flightList);
		 assertEquals(2, flightListExpected.size());
	 }
	 @Test
	 public void getFlightById()
	 {
		   final Flight flight = new Flight("FL-1", "KOC", "TVM", LocalDateTime.now(), LocalDateTime.now());
		   Optional<Flight> optional = Optional.of(flight);
		   Mockito.when(flightRepository.findById("FL-1")).thenReturn(optional);
		   assertThat(flightService.getFlightById("FL-1")).isNotNull();
	 }
	 
}
