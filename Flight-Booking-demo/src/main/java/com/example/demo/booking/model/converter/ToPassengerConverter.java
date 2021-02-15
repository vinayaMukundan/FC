package com.example.demo.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.booking.models.PassengerModel;
import com.example.demo.domain.entity.Passenger;

@Component
public class ToPassengerConverter implements Converter<PassengerModel, Passenger>{

	@Override
	public Passenger convert(PassengerModel source) {
	return new Passenger(null,source.getFirstName(),
			source.getLastName(),
			source.getEmail());
	}

}
