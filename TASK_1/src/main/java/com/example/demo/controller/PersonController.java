package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;
import com.example.demo.service.PersonServiceImplementation;


@RestController
@RequestMapping("/v1/person")
public class PersonController {

	@Autowired
	PersonServiceImplementation personService;
	
	@RequestMapping(value="/create",method =RequestMethod.POST,produces = "application/json")
	public Person createPerson(@RequestBody Person person) {
		return personService.insertPerson(person);
	}
	
	@RequestMapping(value="/update",method =RequestMethod.POST,produces = "application/json")
	public Person updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
	}
	
	@RequestMapping(value="/delete{id}",method =RequestMethod.DELETE,produces = "application/json")
	public void deletePerson(@PathVariable int id) {
		personService.deletePerson(id);
	}
	
	@RequestMapping(value="/findAll",method =RequestMethod.GET,produces = "application/json")
	public List<Person> findAll(){
		return personService.view();
	}
}
