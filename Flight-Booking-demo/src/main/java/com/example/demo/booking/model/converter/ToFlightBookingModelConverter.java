package com.example.demo.booking.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.booking.models.FlightBookingModel;
import com.example.demo.booking.models.FlightModel;
import com.example.demo.booking.models.PassengerModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;
import com.example.demo.domain.entity.Passenger;



@Component
public class ToFlightBookingModelConverter implements Converter<FlightBooking, FlightBookingModel>{

	@Override
	public FlightBookingModel convert(FlightBooking source) {
		
		List<Flight> flights = new ArrayList<>();
		
		if (!CollectionUtils.isEmpty(source.getFlights())) {
			source.getFlights().stream().forEach(flight -> {
				flights.add(new Flight(flight.getId(),flight.getDeparture(), flight.getArrival(),
						flight.getDepartureDate(), flight.getArrivalDate()));
			});
		}

//		return new FlightBookingModel(source.getId(), new Passenger(source.getPassenger().getFirstName(),
//				source.getPassenger().getLastName(), source.getPassenger().getEmail()), flights);
	
		return new FlightBookingModel(source.getId(), new Passenger(source.getId(),source.getPassenger().getFirstName(), 
				source.getPassenger().getLastName(),
				source.getPassenger().getEmail()),
				flights);
			
	}
	
}
