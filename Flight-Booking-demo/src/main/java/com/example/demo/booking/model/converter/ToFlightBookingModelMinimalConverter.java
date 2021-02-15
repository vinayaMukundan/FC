package com.example.demo.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import com.example.demo.booking.models.FlightBookingSummaryModel;
import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;



@Component
public class ToFlightBookingModelMinimalConverter implements Converter<FlightBooking, FlightBookingSummaryModel>{

	@Override
	public FlightBookingSummaryModel convert(FlightBooking source) {
		Flight flight = CollectionUtils.isEmpty(source.getFlights()) ? new Flight()
				: (Flight) source.getFlights().toArray()[0];
		return new FlightBookingSummaryModel(source.getId(), source.getPassenger().getLastName(),
				flight.getDeparture());
	}

	

	
}
