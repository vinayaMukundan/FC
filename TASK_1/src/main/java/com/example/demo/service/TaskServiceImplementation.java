package com.example.demo.service;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Person;
import com.example.demo.models.Task;
import com.example.demo.models.TaskStatus;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskServiceImplementation implements TaskServiceInterface {

	
	TaskRepository taskReopository;
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	public TaskServiceImplementation(TaskRepository taskRepository,PersonRepository personRepository) {
		this.taskReopository=taskRepository;
		this.personRepository = personRepository;
	}
	
	// ***********insert
	public void insertTask(Task task) {
		int flag=0;
		Person person  = task.getAssignedTo();
		int pid = person.getId();
		Person p = new Person(0,"string","string","string");
		if(pid == 0) {
			task.setStatus(TaskStatus.NEW);
			task.setAssignedTo(null);
			taskReopository.save(task);
			
		}
		else {
			List<Task> taskList=taskReopository.findAll();
			List<Person> personList = personRepository.findAll();
			
			for(Task task_obj:taskList){
				System.out.println("inside task table checking"+task_obj);
				if(task_obj.getAssignedTo()!=null) {
				System.out.println("person id inside task tabke :"+task_obj.getAssignedTo());
				
				if(task_obj.getAssignedTo().getId()==pid && task_obj.getStatus()==TaskStatus.DONE) {
						//---person is available 
						task.setStatus(TaskStatus.ASSIGNED);
						task.setAssignedTo(task_obj.getAssignedTo());
						flag =1;
					}else if (task_obj.getAssignedTo().getId()==pid && task_obj.getStatus()==TaskStatus.ASSIGNED){
						task.setStatus(TaskStatus.NEW);
						task.setAssignedTo(null);
						flag =0;
						taskReopository.save(task);
						break;
					}
					
				}
			}//----checking inside task table end
//			
//			
//			//-----check the available id is inside person table 
			while(flag ==1) {
				System.out.println("inside flag 1");
				for(Person person_obj:personList){
					if(person_obj.getId() == pid) {
						taskReopository.save(task);
						}
					}
			}
	}
	}	

	// **********update
	public Task updateTask(Task task) {
		return taskReopository.save(task);
	}  

	// **********delete
	public void deleteTask(int id) {
		taskReopository.deleteById(id);
	}

	// **********view-all
	public List<Task> viewTask() {
		return taskReopository.findAll();
	}

	@Override
	public List<Person> availablePersonList() {
		// TODO Auto-generated method stub

		List<Task> taskList=taskReopository.available_person_list();
		List<Person> person = personRepository.findAll();
	
		for(Task i : taskList) { // ConcurrentModificationException
			   Iterator iterator = person.iterator();
			   while(iterator.hasNext()) {    
			      Person n = (Person) iterator.next();
			      if(n.getId()==i.getAssignedTo().getId()) {
			          iterator.remove();      
			      }
			   }
			}

		for(Person taskk:person) {
		System.out.println(">>>>>>>>>"+taskk);
		}

		return person;
	}
}
