package com.example.demo.domain.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Flight {

	@Id
	@GeneratedValue(generator = "flight-id-gen")
	@GenericGenerator(name = "flight-id-gen", 
		parameters = @Parameter(name = "prefixx", value = "FL"), 
		strategy = "com.example.demo.domain.util.StringSequenceIdGenerator")
	private String id;

	@NotNull
	@NotBlank(message = "departure is mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String departure;
	
	@NotNull
	@NotBlank(message = "arrival is mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String arrival;
	

	private LocalDateTime departureDate;
	private LocalDateTime arrivalDate;
	
	@ManyToMany(mappedBy = "flights",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<FlightBooking> bookings;

	public Flight() {
		super();
	}
	
	public Flight(String id,String departure,String arrival,LocalDateTime departureDate, LocalDateTime arrivalDate) {
		super();
		this.id=id;
		this.departure = departure;
		this.arrival = arrival;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		
	}
	
	@NotBlank
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	/*public Set<FlightBooking> getBookings() {
		return bookings;
	}

	public void setBookings(Set<FlightBooking> bookings) {
		this.bookings = bookings;
	}*/

	@Override
	public String toString() {
		return "Flight [id=" + id + ", departure=" + departure + ", arrival=" + arrival + ", departureDate="
				+ departureDate + ", arrivalDate=" + arrivalDate + "]";
	}

	
}
