package com.example.demo.domain.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.Flight;
import com.example.demo.domain.entity.FlightBooking;

import net.bytebuddy.dynamic.DynamicType.Builder.MethodDefinition.ImplementationDefinition.Optional;


@Transactional
public interface FlightBookingRepository extends JpaRepository<FlightBooking, String>{

//	@Query(DELETE from flight_booking where flight_booking_id IN(select flight_booking_id from 
//			booking_flight where flight_id =?))
	
	
	
//	@Query(Delete f.passenger from FlightBooking where id = ?1)
//	List<FlightBooking> deletePassenger();
	
	List<FlightBooking> findByPassengerId(String passengerId);
	
	
	
}
