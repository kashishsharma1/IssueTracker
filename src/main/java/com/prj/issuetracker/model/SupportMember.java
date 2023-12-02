package com.prj.issuetracker.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="supportinfo")
public class SupportMember extends EmpInfo{
	
	private String speciality;
	
	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer noOfTickets;

	public SupportMember() {
		super();
	}
	
	public SupportMember(String speciality, Integer noOfTickets) {
		super();
		this.speciality = speciality;
		this.noOfTickets = noOfTickets;
	}
	
	public SupportMember(Integer empId, String name, String email, Long phone, Date dob, Date doj, String password,
			String role, Integer noOfTickets) {
		super(empId, name, email, phone, dob, doj, password, role);
		this.speciality = speciality;
		this.noOfTickets = noOfTickets;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Integer getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

}
