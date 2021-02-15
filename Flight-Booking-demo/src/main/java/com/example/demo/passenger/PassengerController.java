package com.example.demo.passenger;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.booking.model.converter.ToPassengerConverter;
import com.example.demo.booking.model.converter.ToPassengerModelConverter;
import com.example.demo.booking.models.PassengerModel;
import com.example.demo.domain.entity.Passenger;


@RestController
@RequestMapping("/passengers")
public class PassengerController {

	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	private ToPassengerConverter toPasseengerConverter;
	
	@Autowired
	private ToPassengerModelConverter toPassengerModelConverter;

	@PostMapping("/insert")  
	private  ResponseEntity<String> savePassengerDetails(@Valid @RequestBody PassengerModel passengerModel)   
	{  
		Passenger passenger = toPasseengerConverter.convert(passengerModel);
		String message = passengerService.insertPassenger(passenger);
		return ResponseEntity.ok(message);
	}
	@PostMapping("/update")  
	private ResponseEntity<String> updatePassengerDetails(@Valid @RequestBody PassengerModel passengerModel)   
	{  
		Passenger passenger = toPasseengerConverter.convert(passengerModel);
		return ResponseEntity.ok(passengerService.updatePassenger(passenger));
	} 
	
	@GetMapping("/getPassengerById/{passenger-id}")
	public @ResponseBody Passenger getPassengerById(@Valid @RequestParam("passenger-id") String passengerId) {
		return passengerService.getPassengerById(passengerId);
		
	}
	
	@GetMapping("/getAllPassengers")
	public @ResponseBody List<PassengerModel> getAllPassengers() {
		List<Passenger> passengerList = passengerService.getAllPassengers();
		List<PassengerModel> passengerModelList = new ArrayList<PassengerModel>();
		for(Passenger passengerObj :passengerList ) {
			PassengerModel passengerModelObj = toPassengerModelConverter.convert(passengerObj);
			passengerModelList.add(passengerModelObj);
		}
		return passengerModelList;
	}
	@GetMapping("/getPassengerByEmail/{email}")
	public @ResponseBody PassengerModel getPassengerByEmail(@PathVariable("email") String email) {
		return toPassengerModelConverter.convert(passengerService.findByEmail(email));
	}
	
	@DeleteMapping("/delete/{passenger-id}")  
	private ResponseEntity<String> deletePassengerDetails(@PathVariable("passenger-id") String passengerId)   
	{  
		String message = passengerService.deletePassenger(passengerId);
		return ResponseEntity.ok(message);
	}  
	
	
	
	
	
	
	
	
	
	
	
	 
	
//	@GetMapping("/getPassengerById/{passenger-id}")
//	public @ResponseBody Passenger getPassengerById(@PathVariable("passenger-id") String passengerId) {
//		System.out.println("inside controller");
//		return passengerService.getPassengerById(passengerId);
//	}
	
	
	
	
	
	
	
}
