package com.ebuero.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bug")
public class Bug implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int STATUS_NEW = 0;
	public static final int STATUS_VERIFIED = 1;
	public static final int STATUS_RESOLVED = 2;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	
	@Column(name="creation_date")
	private Date creationDate;
	
	@JoinColumn(name="assigned_developer")
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=Developer.class)
	private Developer assignedDeveloper;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="status")
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Developer getAssignedDeveloper() {
		return assignedDeveloper;
	}

	public void setAssignedDeveloper(Developer assignedDeveloper) {
		this.assignedDeveloper = assignedDeveloper;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Bug() {
		super();
		// TODO Auto-generated constructor stub
	}

}
