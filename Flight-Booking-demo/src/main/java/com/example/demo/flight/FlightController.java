package com.example.demo.flight;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.booking.BookingService;
import com.example.demo.booking.model.converter.ToFlightConverter;
import com.example.demo.booking.model.converter.ToFlightModelConverter;
import com.example.demo.booking.models.FlightModel;
import com.example.demo.domain.entity.Flight;
import com.sun.istack.NotNull;


@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	private FlightService flightService;
	
	@Autowired
	private ToFlightModelConverter toFlightModelConverter;
	
	@Autowired
	private ToFlightConverter toFlightConverter;
	
	/*@Autowired
	private BookingService bookingService;*/
	
	
	@PostMapping("/insert")  
	private ResponseEntity<String> saveFlight(@Valid @RequestBody FlightModel flightModel)   
	{  
		String message = flightService.insertFlight(toFlightConverter.convert(flightModel)); 
		return ResponseEntity.ok(message);
	}
	@PostMapping("/update")  
	private ResponseEntity<String> updateFlight(@Valid @RequestBody Flight flight)   
	{  
		String message = flightService.updateFlight(flight);
		return ResponseEntity.ok(message);
	}
	
	
	
	
	
	
	@GetMapping
	public @ResponseBody List<FlightModel> getAllFlights() {
		List<Flight> flightList = flightService.getAllFlights();
		List<FlightModel> flightModelList = new ArrayList<FlightModel>();
		for(Flight flighObj :flightList ) {
			FlightModel flightModelObj = toFlightModelConverter.convert(flighObj);
			flightModelList.add(flightModelObj);
		}
		return flightModelList;
	}
	@GetMapping("/{flight-id}")
	public @ResponseBody FlightModel getFlightById(@PathVariable("flight-id") @NotNull String flightId) {
		return toFlightModelConverter.convert(flightService.getFlightById(flightId));
	}
	@DeleteMapping("/delete/{flight-id}")  
	private ResponseEntity<String> deleteFlight(@PathVariable("flight-id") @NotNull String flightId)   
	{  
		String message =flightService.deleteFlight(flightId);
		return ResponseEntity.ok(message);
	}  
	@GetMapping("/findDepartureAndAfterADate/{departure}/{departureDate}")
	public @ResponseBody List<Flight> findByDateAndPlace(@PathVariable("departure")@NotNull String departure, @PathVariable("departureDate") @NotNull String departureDate)
	{
		System.out.println("inside flight controller");
		DateTimeFormatter GlobalDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime.parse(departureDate, GlobalDateFormatter);
		return flightService.findByDepartureAndDepartureDateGreaterThan(departure, LocalDateTime.parse(departureDate, GlobalDateFormatter));
	}
}
