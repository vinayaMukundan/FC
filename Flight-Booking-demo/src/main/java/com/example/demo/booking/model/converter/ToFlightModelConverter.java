package com.example.demo.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.booking.models.FlightModel;
import com.example.demo.domain.entity.Flight;

@Component
public class ToFlightModelConverter implements Converter<Flight, FlightModel>{

	@Override
	public FlightModel convert(Flight source) {
		
		return new FlightModel(source.getDeparture(), source.getArrival(), source.getArrivalDate(), 
				source.getDepartureDate());
				
	}
}
