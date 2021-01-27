package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;
import com.example.demo.models.Task;
import com.example.demo.service.TaskServiceImplementation;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
	
	@Autowired
	TaskServiceImplementation taskService;
	
	
	@RequestMapping(value="/createTask",method =RequestMethod.POST,produces = "application/json")
	public void createTask(@RequestBody Task task) {
		if("task.getStatus()".matches("NEW")) {
			System.out.println("person det "+task.getAssignedTo());
		}
		 taskService.insertTask(task);
	}
	
	@RequestMapping(value="/updateTask",method =RequestMethod.POST,produces = "application/json")
	public Task updateTask(@RequestBody Task task) {
		return taskService.updateTask(task);
	}
	
	@RequestMapping(value="/delete{tid}",method =RequestMethod.DELETE,produces = "application/json")
	public void deleteTask(@PathVariable int tid) {
		taskService.deleteTask(tid);
	}
	
	@RequestMapping(value="/findAll-task",method =RequestMethod.GET,produces = "application/json")
	public List<Task> findAll(){
		return taskService.viewTask();
	}

	@RequestMapping(value="/findAll-available-task",method =RequestMethod.GET,produces = "application/json")
	public List<Person> findAll_available_person(){
		return taskService.availablePersonList();
	}
	
}
