package com.prj.issuetracker.service;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prj.issuetracker.model.EmpInfo;
import com.prj.issuetracker.model.SupportMember;
import com.prj.issuetracker.model.Tickets;
import com.prj.issuetracker.repository.SupportRepository;
import com.prj.issuetracker.repository.TicketsRepository;

@Service
public class TicketServiceImp implements TicketService{
	
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@Autowired
	private SupportRepository supportRepository;
	
	@Override
	public List<Tickets> findByEmpidPending(Integer empid) {
		// TODO Auto-generated method stub
		return ticketsRepository.findByEmpidPending(empid);
	}

	@Override
	public List<Tickets> findByEmpidResolved(Integer empid) {
		// TODO Auto-generated method stub
		return ticketsRepository.findByEmpidResolved(empid);
	}
	
	@Override
	@Transactional
	public void addNewTicket(Integer empId, String issueType, Integer supportID, Time ticketTime, Date ticketDate) {
		// TODO Auto-generated method stub
		Tickets tickObj = new Tickets();
		tickObj.setEmpId(empId);
		tickObj.setType(issueType);
		tickObj.setStatus("pending");
		tickObj.setSupId(supportID);
		tickObj.setCreationTime(ticketTime);
		tickObj.setCreationDate(ticketDate);
		ticketsRepository.save(tickObj);
		return;
	}

	@Override
	public List<Tickets> findBySupidResolved(Integer empid) {
		return ticketsRepository.findTicketsBySupidResolved(empid);
	}

	@Override
	public List<Tickets> findTicketsBySupidPending(Integer empid) {
		return ticketsRepository.findTicketsBySupidPending(empid);

	}

	@Override
	public void updateNoOfTickets(Integer empid) {
		// TODO Auto-generated method stub
		SupportMember existingSupport = supportRepository.findSupportMemberById(empid);
		Integer noOfTickets = existingSupport.getNoOfTickets();
		if (noOfTickets > 0) {
			noOfTickets--;
		}
		existingSupport.setNoOfTickets(noOfTickets);
		supportRepository.save(existingSupport);
	}
	
	@Override
	public Tickets findByTicketId(Integer ticketId) {
		// TODO Auto-generated method stub
		return ticketsRepository.findById(ticketId).orElse(null);
	}
	
	@Override
	public void updateTicketInfo(Integer ticketId, String feedback, String choice, Integer empid) {
		Tickets existingTicket  = findByTicketId(ticketId);
		log.info(existingTicket.toString());
		log.info("updating ticket");
		existingTicket.setMessage(feedback);
		existingTicket.setStatus("resolved");
//		existingTicket.setResolvedTime(LocalDateTime.now());
		log.info("saving ticket");
		ticketsRepository.save(existingTicket);
		
	}
	
}
