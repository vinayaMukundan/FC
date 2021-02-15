package com.example.demo.passenger;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Passenger;
import com.example.demo.domain.repo.PassengerRepository;




@Service
@Transactional
public class PassengerServiceImp implements PassengerService{

	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	public PassengerServiceImp(PassengerRepository passengerRepo) {
		this.passengerRepo = passengerRepo;
	}
	
	@Override
	public String insertPassenger(Passenger passenger) {
		String message;
		if(passenger.getId()==null) {
			passengerRepo.save(passenger);
			message = "Passenger details Added Successfully ...";
		}else {
			message = "Insertion Failed ...! Try Again ...!!";
		}
		return message;
	}
	@Override
	public String updatePassenger(Passenger passenger) {
		//getPassengerById(passenger.getId());
		passengerRepo.save(passenger);
		return "Passenger Details updated Successfully ...!";
	}
	
	@Override
	public Passenger getPassengerById(String passengerId) {
		return passengerRepo.findById(passengerId).orElseThrow(() -> new PassengerNotFoundException(passengerId));
	}
	
	@Override
	public List<Passenger> getAllPassengers() {
		return passengerRepo.findAll();
	}
	
	@Override
	public String deletePassenger(String id) {
		passengerRepo.deleteById(id);
		return "Passenger Deleted Successfully...!!";
	}
	@Override
	public Passenger findByEmail(String email) {
		return passengerRepo.findByEmail(email);
	}
}
