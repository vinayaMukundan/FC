package com.example.demo.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Passenger;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String>{

	@Query(value = "select * from passenger p where p.email = ?1",nativeQuery = true )
	Passenger findByEmail(String email);
	
}
