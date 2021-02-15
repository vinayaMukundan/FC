package com.example.demo.booking.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.booking.models.PassengerModel;
import com.example.demo.domain.entity.Passenger;

@Component
public class ToPassengerModelConverter implements Converter<Passenger,PassengerModel>{

	@Override
	public PassengerModel convert(Passenger source) {
		return new PassengerModel(source.getFirstName(),
				source.getLastName(), source.getEmail());
	}

}
