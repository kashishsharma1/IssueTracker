package com.prj.issuetracker.service;

import java.util.List;

import com.prj.issuetracker.model.EmpInfo;

public interface EmployeeService {
	EmpInfo getEmployee(int empid);
	List<EmpInfo> getAll();
	void updateEmployeeInfo(int empId, EmpInfo employee);
	void updatePassword(Integer empId, String password);
	void updatePhone(Integer empId, Long updatedNumber);
}
