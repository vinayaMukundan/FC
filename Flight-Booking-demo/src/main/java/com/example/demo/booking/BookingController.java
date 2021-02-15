package com.example.demo.booking;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.booking.model.converter.ToFlightBookingConverter;
import com.example.demo.booking.model.converter.ToFlightBookingModelConverter;
import com.example.demo.booking.models.FlightBookingModel;
import com.example.demo.booking.models.FlightBookingSummaryModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;




@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ToFlightBookingModelConverter toBookingModelConverter;
	
	@Autowired
	private ToFlightBookingConverter toBookingConverter;
	
	@PostMapping("/insert")  
	private void saveBooking(@Valid @RequestBody FlightBookingModel bookingModel)   
	{  
		System.out.println("saveBooking"+bookingModel);
		FlightBooking fb = toBookingConverter.convert(bookingModel);
		System.out.println(fb);
		bookingService.insertBooking(fb);
		//return null;
	} 
	@PostMapping("/update")  
	private void updateBooking(@Valid @RequestBody FlightBookingModel bookingModel)   
	{  
		System.out.println("saveBooking"+bookingModel);
		FlightBooking fb = toBookingConverter.convert(bookingModel);
		System.out.println(fb);
		bookingService.updateBooking(fb);
		//return null;
	} 
	@GetMapping("/")
	public @ResponseBody List<FlightBookingModel> getAllBookingDetails() {
		logger.info("Finding booking by id ... " );
		//bookingService.getAllMultiFlightBookings();
		
		List<FlightBooking> flightBookings = bookingService.getAllMultiFlightBookings();

		List<FlightBookingModel> flightBookingModel = new ArrayList<FlightBookingModel>();
		for(FlightBooking flightBooking :flightBookings ) {
			FlightBookingModel flightModel = toBookingModelConverter.convert(flightBooking);
			flightBookingModel.add(flightModel);
		}
		return flightBookingModel;
	}
	
	@GetMapping("/{bookingId}")
	public @ResponseBody FlightBookingModel getBookingById(@RequestParam @Valid @NotEmpty String bookingId) {
		logger.info("Finding booking by id ... " + bookingId);
		return toBookingModelConverter.convert(bookingService.getBooking(bookingId));
	}
	
	@DeleteMapping("delete/{bookingId}")
	public void deleteBookingById(@PathVariable("bookingId") String model) {

		logger.info("Finding booking by id ... " + model);
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<"+model);
		
		//toBookingModelConverter.convert(bookingService.deleteBooking(bookingId));
		bookingService.deleteBookingbyFlightid(model);
	}
	
	@GetMapping("getAllBookingByPassengerId")
	public @ResponseBody List<FlightBookingSummaryModel> getBookings(
			@RequestParam(required = false, name = "uid") String passengerId, 
			@RequestParam(required = false, name = "multi-flights", defaultValue = "false") boolean multiFlights) {

		logger.info("Finding booking by passengerId ... " + passengerId);
		logger.info("Finding booking by passengerId ... " + multiFlights);

		List<FlightBooking> bookings = null;
		
		if(!StringUtils.isEmpty(passengerId)) {
				System.out.println("000000000000000------------get all  Booking by passenger  ");
				bookings = bookingService.getAllBookingsByPassenger(passengerId);
		} else {
			throw new BookingNotFoundException(null);
		}
		
		List<FlightBookingSummaryModel> bookingModels = new ArrayList<>(bookings.size());
		bookings.stream().forEach(booking -> {
			bookingModels.add(new FlightBookingSummaryModel(booking.getId(), booking.getPassenger().getLastName(),
					((Flight) booking.getFlights().toArray()[0]).getDeparture()));
		});
		return bookingModels;
	}

}
