package com.example.demo.domain.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String>{

	@Query(value = "select f from Flight f where f.departure =?1 and f.departureDate >= ?2")
	List<Flight> findByDepartureAndDepartureDateGreaterThan(String departure, LocalDateTime departureDate);
	
}
