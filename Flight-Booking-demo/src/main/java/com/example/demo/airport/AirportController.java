package com.example.demo.airport;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.entity.Airport;



@RestController
@RequestMapping("/airports")
public class AirportController {
	
	@Autowired
	private AirportService airportService;
	
	@PostMapping("/insert")
	public ResponseEntity<String> saveAirport(@RequestBody @Valid Airport airport)   
	{  
		String message = airportService.insertAirport(airport);
		return ResponseEntity.ok(message);
	}
	
	@PostMapping("/update")  
	private ResponseEntity<String> updateAirport(@RequestBody @Valid Airport airport)   
	{  
		String message = airportService.updateAirport(airport);
		return ResponseEntity.ok(message);
	}  
	
	@GetMapping("/getAirport/{iata-code}")
	public ResponseEntity<Airport> getAirportById(@PathVariable("iata-code") @NotBlank String iataCode)throws Exception{
	  Airport airport = airportService.getAirportById(iataCode);
	  return new ResponseEntity<Airport>(airport, HttpStatus.OK);
	}
	
	@GetMapping("/findAll")
	public @ResponseBody List<Airport> getAllAirports() {
		return airportService.getAllAirports();
	}
	
	@DeleteMapping("/delete/{iata-code}")  
	private ResponseEntity<String> deleteAirport(@PathVariable("iata-code") @NotBlank String iataCode)   
	{  
		String message = airportService.deleteAirport(iataCode);
		return ResponseEntity.ok(message);
	}  
	
}
