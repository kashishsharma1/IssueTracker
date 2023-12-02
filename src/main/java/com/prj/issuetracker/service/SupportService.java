package com.prj.issuetracker.service;

public interface SupportService {
	public Integer getSupportID(String type);
	public void UpdateNoOfTickets(Integer supId);
	public Integer displayCountPending(Integer empid);
	public Integer displayCountResolved(Integer empid);
	public Integer displayCountTotal(Integer empid);
	public void updateNoOfTickets(Integer empid);
}
