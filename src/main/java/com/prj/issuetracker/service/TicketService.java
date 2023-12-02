package com.prj.issuetracker.service;
import java.sql.Date;
import java.sql.Time;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.prj.issuetracker.model.Tickets;

public interface TicketService {
	List<Tickets> findByEmpidPending(Integer empid);
	List<Tickets> findByEmpidResolved(Integer empid);
	List<Tickets> findBySupidResolved(Integer empid);//gives the resolved issue of support team member
	List<Tickets> findTicketsBySupidPending(Integer employeeId);
	public void updateNoOfTickets(Integer empid);
	void addNewTicket(Integer empId, String issueType, Integer supportID, Time ticketTime, Date ticketDate);
	void updateTicketInfo(Integer ticketId, String feedback, String choice, Integer empid);
	Tickets findByTicketId(Integer ticketId);
}
