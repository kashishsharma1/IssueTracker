package com.prj.issuetracker.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prj.issuetracker.model.EmpInfo;
import com.prj.issuetracker.model.SupportMember;
import com.prj.issuetracker.repository.SupportRepository;
import com.prj.issuetracker.repository.TicketsRepository;

@Service
public class SupportServiceImp implements SupportService{

	@Autowired
	private SupportRepository supportRepository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	
	@Override
	public Integer getSupportID(String speciality) {
		// TODO Auto-generated method stub
		return supportRepository.findSupIdByType(speciality);
	}



	@Override
	@Transactional
	public void UpdateNoOfTickets(Integer supId) {
		SupportMember supportMember = supportRepository.findById(supId).orElse(null);
		supportMember.setNoOfTickets(supportMember.getNoOfTickets()+1);
		supportRepository.save(supportMember);
		return;
		// TODO Auto-generated method stub
		
	}



	@Override
	public Integer displayCountPending(Integer empid) {
		// TODO Auto-generated method stub
		return ticketsRepository.countByEmpIdAndStatusPending(empid);
	}



	@Override
	public Integer displayCountResolved(Integer empid) {
		// TODO Auto-generated method stub
		return ticketsRepository.countByEmpIdAndStatusResolved(empid);
	}



	@Override
	public Integer displayCountTotal(Integer empid) {
		// TODO Auto-generated method stub
		return ticketsRepository.countByEmpId(empid);
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
}
