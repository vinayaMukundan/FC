package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
	

	@Query(value = "select * from task where status ='ASSIGNED'",nativeQuery = true)
	public List<Task> available_person_list();
	
	
}
