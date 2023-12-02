package com.prj.issuetracker;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.prj.issuetracker.model.EmpInfo;
import com.prj.issuetracker.model.Tickets;
import com.prj.issuetracker.service.EmployeeService;
import com.prj.issuetracker.service.SupportService;
import com.prj.issuetracker.service.TicketService;

@Controller
public class EmployeeController {
	
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");
	
	@Autowired
	EmployeeService empObject;
	
	@Autowired
	TicketService ticketObject;
	
	@Autowired 
	SupportService supportObject;
	
	@GetMapping("/raiseticket")
	public String raiseATicket(HttpServletRequest request) {
		try {
		String typeOfTicket = request.getParameter("ticketType");
		log.info(typeOfTicket);
		
		HttpSession session = request.getSession(false);
		Integer curr_userid = (Integer) session.getAttribute("userid");
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		
		Integer supportMemberId = supportObject.getSupportID(typeOfTicket);
		supportObject.UpdateNoOfTickets(supportMemberId);
		String creationTime = request.getParameter("clickTime");
		String creationDate = request.getParameter("clickDate");
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
            // Parse the input string into a Date object
            java.util.Date time = sdf.parse(creationTime);
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(creationDate);

            // Convert the Date object into a SQL Time object
            Time sqlTimeOfTicket = new Time(time.getTime());
            Date sqlDateOfTicket = new Date(date.getTime());
            
            ticketObject.addNewTicket(curr_userid, typeOfTicket, supportMemberId, sqlTimeOfTicket, sqlDateOfTicket);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		
		return "redirect:/employee";
	}
	
	@GetMapping("/employee")
	public String showEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		return "employee";
	}
	
	@GetMapping("/employeePendingIssues")
	public String showPendingIssues(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		List<Tickets> pendingTickets = ticketObject.findByEmpidPending(curr_userid);
		request.setAttribute("pendingissues", pendingTickets);
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		return "employeePendingIssues";
	}
	@GetMapping("/employeeResolvedIssues")
	public String showResolvedIssues(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		List<Tickets> resolvedTickets = ticketObject.findByEmpidResolved(curr_userid);
		request.setAttribute("resolvedissues", resolvedTickets);
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		return "employeeResolvedIssues";
	}
	
}
