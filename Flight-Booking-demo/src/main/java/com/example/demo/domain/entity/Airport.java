package com.example.demo.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Airport {
	
	@Id
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String iataCode;

	
	@NotBlank(message = "Name is mandatory")
	@NotNull
	@Size(min = 2 ,message = "name should have atleast 2 characters")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String name;
	
	@NotNull
	@NotBlank(message = "countryIsoCode is mandatory")
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String countryIsoCode;
	
	public Airport() {
		super();
	}
	public Airport(String iataCode, String name, String countryIsoCode) {
		super();
		this.iataCode = iataCode;
		this.name = name;
		this.countryIsoCode = countryIsoCode;
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryIsoCode() {
		return countryIsoCode;
	}
	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}
	@Override
	public String toString() {
		return "Airport [iataCode=" + iataCode + ", name=" + name + ", countryIsoCode=" + countryIsoCode + "]";
	}
}
