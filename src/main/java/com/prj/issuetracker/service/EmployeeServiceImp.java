package com.prj.issuetracker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.issuetracker.model.EmpInfo;
import com.prj.issuetracker.repository.EmpRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");

	@Autowired
	private EmpRepository employeeRepository;

	@Override
	public EmpInfo getEmployee(int empId) {
		return employeeRepository.findByEmpid(empId).orElse(null);
	}

	@Override
	public List<EmpInfo> getAll() {
		return employeeRepository.findAll();
	}
	
	
	@Override
	public void updateEmployeeInfo(int empId, EmpInfo employee) {


		EmpInfo existingEmployee = getEmployee(empId);
		log.info("employee before updating " + existingEmployee);

		if (existingEmployee != null) {
			// Update the employee information
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setPassword(employee.getPassword());
			// Save the updated employee
			employeeRepository.save(existingEmployee);
		}
		log.info("updated employee " + existingEmployee);


	}
	

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		EmployeeServiceImp.log = log;
	}

	public EmpRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmpRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	

	@Override
	@Transactional
	public void updatePassword(Integer empId, String password) {
		EmpInfo empInstance = employeeRepository.findById(empId).orElse(null);
		empInstance.setPassword(password);
		employeeRepository.save(empInstance);
	}
	
	@Override
	@Transactional
	public void updatePhone(Integer empId, Long updatedNumber) {
		EmpInfo empInstance = employeeRepository.findById(empId).orElse(null);
		empInstance.setPhone(updatedNumber);
		employeeRepository.save(empInstance);
	}
	

}
