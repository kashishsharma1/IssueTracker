package com.prj.issuetracker.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tickets")
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ticketId;
	private String type;
	private Integer empId;
	private int supId;
	private Time creationTime;
	private Date creationDate;
	
	private String status;
	private Time actionTime;
	private Time resolvedTime;
	private String message;
	
	
	public Tickets(int ticketId, String type, int empId, int supId, Time creationTime, String status,
			Time actionTime, Time resolvedTime, String message) {
		super();
		this.ticketId = ticketId;
		this.type = type;
		this.empId = empId;
		this.supId = supId;
		this.creationTime = creationTime;
		this.status = status;
		this.actionTime = actionTime;
		this.resolvedTime = resolvedTime;
		this.message = message;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Time getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}


	public Tickets() {
		super();
	}
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Time getActionTime() {
		return actionTime;
	}
	public void setActionTime(Time actionTime) {
		this.actionTime = actionTime;
	}
	public Time getResolvedTime() {
		return resolvedTime;
	}
	public void setResolvedTime(Time resolvedTime) {
		this.resolvedTime = resolvedTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}