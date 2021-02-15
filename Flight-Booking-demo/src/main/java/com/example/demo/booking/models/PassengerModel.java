package com.example.demo.booking.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PassengerModel {
	
	@NotEmpty
	@NotBlank(message = "first name is mandatory")
	@Size(max =20 ,message = "name should be less than 20 Characters ")
	private String firstName;
	@NotEmpty
	@NotNull
	@NotBlank(message = "lastname is mandatory")
	private String lastName;
	@NotEmpty
	//@Email
	@NotNull
	@NotBlank(message = "EMAIL is mandatory")
	private String email;

	public PassengerModel(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PassengerModel [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
