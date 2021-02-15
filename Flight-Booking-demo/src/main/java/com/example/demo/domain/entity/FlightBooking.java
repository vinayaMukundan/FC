 package com.example.demo.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
public class FlightBooking implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "booking-id-gen")
	@GenericGenerator(name = "booking-id-gen", parameters = @Parameter(name = "prefixx", value = "FB"), strategy = "com.example.demo.domain.util.StringSequenceIdGenerator")
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Passenger passenger;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "booking_flights", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "flight_id"))
	private Set<Flight> flights;

	
//	
//	public void addFlight(Flight flight) {
//        this.flights.add(flight);
//        flight.getBookings().add(this);
//    }
//  
//    public void removeFlight(Flight flight) {
//        this.flights.remove(flight);
//        flight.getBookings().remove(this);
//    }
//	
	
	public FlightBooking() {
		super();
	}

	public FlightBooking(String id, Passenger passenger, Set<Flight> flights) {
		super();
		this.id = id;
		this.passenger = passenger;
		this.flights = flights;
	}

	@NotBlank
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public Set<Flight> getFlights() {
		return flights;
	}

	public FlightBooking(Set<Flight> flights) {
		super();
		this.flights = flights;
	}

	@Override
	public String toString() {
		return "FlightBooking [id=" + id + ", passenger=" + passenger + ", flights=" + flights + "]";
	}

	
}
