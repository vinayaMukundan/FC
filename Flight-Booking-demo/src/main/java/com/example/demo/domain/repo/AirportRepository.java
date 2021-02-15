package com.example.demo.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entity.Airport;


@Repository
public interface AirportRepository extends JpaRepository<Airport, String>{

}
