package com.example.demo.airport;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.entity.Airport;
import com.example.demo.domain.repo.AirportRepository;


@Service
@Transactional
public class AirportServiceImp implements AirportService{
	
	@Autowired
	AirportRepository airportRepo;
	
	@Autowired
	public AirportServiceImp(AirportRepository airportRepo) {
		this.airportRepo = airportRepo;
	}
	
	
	@Override
	public Airport getAirportById(String iataCode) {
		return airportRepo.findById(iataCode).orElseThrow(() -> new AirportNotFoundException(iataCode));
	}
	
	@Override
	public String insertAirport(Airport airport) {
		String message;
		if(airport.getIataCode()== "" || airport.getIataCode() == null) {
			message = "Insertion Failed ...! Try Again ...!!";
		}else {
			airportRepo.save(airport);
			message = "Airport details Added Successfully ...";
		}
		return message;	
	}
	
	@Override
	public String updateAirport(Airport airport) {
		//getAirportById(airport.getIataCode());
		airportRepo.save(airport);
		return "Airport Updated Successfully...!!";
	}
	
	@Override
	public List<Airport> getAllAirports() {
		return airportRepo.findAll();
	}
	
	@Override
	public String deleteAirport(String iataCode) {
		//getAirportById(iataCode);
		airportRepo.deleteById(iataCode);
		return "Airport Deleted Successfully...!!";
	}
}
