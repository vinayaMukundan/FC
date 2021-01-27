package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Person;
import com.example.demo.repository.PersonRepository;

@Service
public class PersonServiceImplementation implements PersonServiceInterface{
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	public PersonServiceImplementation(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	
	//***********insert
	public Person insertPerson(Person person) {
		return personRepository.save(person);
	}
	
	//**********update
	public Person updatePerson(Person person) {
		return personRepository.save(person);
	}
	
	//**********delete
	public void deletePerson(int id) {
		personRepository.deleteById(id);
	}
	
	//**********view-all
	public List<Person> view(){
		return personRepository.findAll();
	}

}
