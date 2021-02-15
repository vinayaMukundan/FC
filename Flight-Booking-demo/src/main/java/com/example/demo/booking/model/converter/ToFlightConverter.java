package com.example.demo.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.booking.models.FlightModel;
import com.example.demo.domain.entity.Flight;

@Component
public class ToFlightConverter implements Converter<FlightModel, Flight>{

	@Override
	public Flight convert(FlightModel source) {
		return new Flight(null,source.getDeparture(),
				source.getArrival(), 
				source.getDepartureDate(),
				source.getArrivalDate());
	}

}
