package com.example.demo.booking.model.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class ToFlightBookingConverter implements Converter<FlightBookingModel, FlightBooking>{

	@Override
	public FlightBooking convert(FlightBookingModel source) {
		
		Set<Flight> set = new HashSet<>(); 
		
		if (!CollectionUtils.isEmpty(source.getFlights())) {
			source.getFlights().stream().forEach(flight -> {
				set.add(new Flight(flight.getId(),flight.getDeparture(), flight.getArrival(),
						flight.getDepartureDate(), flight.getArrivalDate()));
			});
		}
		Passenger passenger = source.getPassenger();
//		ToPassengerConverter toPassengerConverter = new ToPassengerConverter();
//		Passenger passenger = toPassengerConverter.convert(passengerModel);
		return new FlightBooking(null,passenger, set);
	}
}
