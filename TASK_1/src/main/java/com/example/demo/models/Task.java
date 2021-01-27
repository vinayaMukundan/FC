package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task {

	@Id
	@GeneratedValue
	@Column(name = "tid")
	private int tid;
	@Column(name = "tname")
	private String tname;
	@Column(name = "tdescription")
	private String tdescription;

	@ManyToOne
	private Person assignedTo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TaskStatus status;

	public Task(int tid, String tname, String tdescription, Person assignedTo, TaskStatus status) {
		super();
		this.tid = tid;
		this.tname = tname;
		this.tdescription = tdescription;
		this.assignedTo = assignedTo;
		this.status = status;
	}

	public Task() {
		super();
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTdescription() {
		return tdescription;
	}

	public void setTdescription(String tdescription) {
		this.tdescription = tdescription;
	}

	public Person getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Person assignedTo) {
		this.assignedTo = assignedTo;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Task [tid=" + tid + ", tname=" + tname + ", tdescription=" + tdescription + ", assignedTo=" + assignedTo
				+ ", status=" + status + "]";
	}

}
