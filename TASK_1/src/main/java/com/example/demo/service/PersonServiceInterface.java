package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Person;

public interface PersonServiceInterface {

	
	Person insertPerson(Person person);
	Person updatePerson(Person person);
	void deletePerson(int id);
	List<Person> view();
	
}
