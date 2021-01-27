package com.example.demo.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.models.Person;
import com.example.demo.models.Task;
import com.example.demo.models.TaskStatus;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;

@ExtendWith(SpringExtension.class)
public class TaskServiceTest {
	
	@InjectMocks
	TaskServiceInterface taskService;
	
	@Mock
	TaskRepository taskRepository;
	@Mock
	PersonRepository personRepository;
	
	public TaskServiceTest()
	{
		taskRepository=mock(TaskRepository.class);
		personRepository=mock(PersonRepository.class);
		taskService=new TaskServiceImplementation(taskRepository,personRepository);
		
	}

	
	
	@Test
	public void taskUpdate() {
		
		Task task=new Task(1,"Addingg","Adding datas",new Person(1,"aa","aa","aaa"),TaskStatus.ASSIGNED);
		taskService.updateTask(task);
		verify(taskRepository).save(task);
	}
	
	
	 @Test
	    public void testTaskDeleteById() {
	        int id=42;
	        taskService.deleteTask(id);
	        verify(taskRepository, times(1)).deleteById(id);;
	    }
	
	
		
	@Test
	public void taskInsertTestWithPersonId() {
		Person p = new Person(0,"string","string","string");
		Task task=new Task(1,"Add","Adding datas",new Person(0,"asd","djhfd","bsddchjbdj"),TaskStatus.ASSIGNED);
		taskService.insertTask(task);
		verify(taskRepository).save(task);
	}
	
	
	@Test
	 public void testTaskFindAll() {
	 List task_list = new LinkedList();
	 
	 task_list.add(new Task(1,"Task_description","Task_name",new Person(1,"vinaya","km","vinaya@123"),TaskStatus.DONE));
	 task_list.add(new Task(2,"TaskDetails","Task_name",new Person(2,"vinaya","km","vinaya@123"),TaskStatus.DONE));
	 when(taskRepository.findAll()).thenReturn(task_list); 
	 List result = taskService.viewTask();
	 verify(taskRepository).findAll();
	 }
	
	
	@Test
	 public void testTaskFindAllPerson() {
		
	List<Person> person_list=new ArrayList<Person>();
	List<Task> task_list= new ArrayList<Task>();
	task_list.add((new Task(1,"Task_description","Task_name",new Person(1,"vinaya","km","vinaya@123"),TaskStatus.DONE)));
	 when(taskRepository.available_person_list()).thenReturn(task_list);  
	 List result = taskService.availablePersonList();
	 verify(taskRepository).available_person_list();
	 
	 
	 }
	
	
	
}
