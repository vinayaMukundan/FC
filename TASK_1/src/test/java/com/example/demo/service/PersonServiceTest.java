package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.models.Person;
import com.example.demo.repository.PersonRepository;


@ExtendWith(SpringExtension.class)
public class PersonServiceTest {

	@InjectMocks
	PersonServiceInterface personService;
	
	@Mock
	PersonRepository personRepository;
	
	public PersonServiceTest()
	{
		personRepository=mock(PersonRepository.class);
		personService=new PersonServiceImplementation(personRepository);
	}
	
	@Test
	public void testInertPersonDetails() {	
		Person person=new Person(1,"Vinaya","Mukundan","vinaya12345");
		personService.insertPerson(person);
		verify(personRepository).save(person);
	}
	
	@Test
	public void testPersonUpdate() {
		Person person=new Person(1,"Vinaya","KT","12457");
		personService.updatePerson(person);
		verify(personRepository).save(person);
	}
	
	@Test
	 public void testPersonFindAll() {
	 List person_list = new LinkedList();
	 person_list.add(new Person(1,"John","Doe","password@123"));
	 person_list.add(new Person(2,"Vinaya","KT","username@12457"));
	 when(personRepository.findAll()).thenReturn(person_list); 
	 List result = personService.view();
	 verify(personRepository).findAll();
	 }
	
	 @Test
	    public void testPersonDeleteById() {
	        int id=42;
	        personService.deletePerson(id);
	        verify(personRepository, times(1)).deleteById(id);;
	    }
	
}
