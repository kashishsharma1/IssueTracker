package com.prj.issuetracker.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="empinfo")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class EmpInfo {
	@SequenceGenerator(name = "emp_seq", initialValue = 741400, allocationSize = 5)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
	private Integer empid;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private Long phone;
	
	@Column(nullable = false)
	private Date dob;

	@Column(nullable = false)
	private Date doj;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String role;

	public EmpInfo() {
		super();
	}

	public EmpInfo(Integer empId, String name, String email, Long phone, Date dob, Date doj, String password,
			String role) {
		super();
		this.empid = empId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.doj = doj;
		this.password = password;
		this.role = role;
	}

	public Integer getEmpId() {
		return empid;
	}

	public void setEmpId(Integer empId) {
		this.empid = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
