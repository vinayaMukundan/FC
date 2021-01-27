package com.example.demo.service;

import java.util.List;

import com.example.demo.models.Person;
import com.example.demo.models.Task;

public interface TaskServiceInterface {

	void insertTask(Task task);
	Task updateTask(Task task);
	void deleteTask(int id);
	List<Task> viewTask();
	List<Person> availablePersonList();
	
}
