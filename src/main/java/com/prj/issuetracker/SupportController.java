package com.prj.issuetracker;


import java.util.List;

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
public class SupportController {
	
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");
	
	@Autowired
	EmployeeService empObject;
	
	@Autowired
	TicketService ticketObject;
	
	@Autowired 
	SupportService supportObject;

	@GetMapping("/support")
	public String showEmployee(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		List<Tickets> pendingTickets = ticketObject.findTicketsBySupidPending(curr_userid);
		request.setAttribute("pendingissues", pendingTickets);
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		request.setAttribute("pendingIssues", supportObject.displayCountPending(curr_userid));
		request.setAttribute("resolvedIssues", supportObject.displayCountResolved(curr_userid));
		request.setAttribute("totalIssues", supportObject.displayCountTotal(curr_userid));
		return "supportDashboard";
	}
	
	@GetMapping("/supportResolvedIssues")
	public String showResolvedIssues(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		List<Tickets> resolvedTickets = ticketObject.findBySupidResolved(curr_userid);
		request.setAttribute("resolvedissues", resolvedTickets);
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		request.setAttribute("supPendingIssues", supportObject.displayCountPending(curr_userid));
		request.setAttribute("supResolvedIssues", supportObject.displayCountResolved(curr_userid));
		request.setAttribute("totalIssues", supportObject.displayCountTotal(curr_userid));
		return "supportResolved";
	}
	
	@GetMapping("/supportDashboard")
	public String supportPending(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("username", curr_emp.getName());
		request.setAttribute("role", curr_emp.getRole());
		request.setAttribute("pendingIssues", supportObject.displayCountPending(curr_userid));
		request.setAttribute("resolvedIssues", supportObject.displayCountResolved(curr_userid));
		request.setAttribute("totalIssues", supportObject.displayCountTotal(curr_userid));
		request.setAttribute("displayPendingIssues", ticketObject.findTicketsBySupidPending(curr_userid));
		request.setAttribute("displayResolvedIssues", ticketObject.findBySupidResolved(curr_userid));
		session.setAttribute("displayPendingIssues", request.getAttribute("displayPendingIssues"));
		session.setAttribute("displayResolvedIssues", request.getAttribute("displayResolvedIssues"));
		session.setAttribute("totalIssues", request.getAttribute("totalIssues"));
		session.setAttribute("resolvedIssues", request.getAttribute("resolvedIssues"));
		session.setAttribute("pendingIssues", request.getAttribute("pendingIssues"));
		session.setAttribute("role", request.getAttribute("role"));
		session.setAttribute("username", request.getAttribute("username"));
		return "supportDashboard";
	}
	
	@PostMapping("/getMessage")
	public String retrieveMessage(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		log.info(request.getParameter("buttonId"));
		log.info(request.getParameter("choice"));
		session.setAttribute("ticketId", request.getParameter("buttonId"));
		session.setAttribute("choice", request.getParameter("choice"));
		
		return "supportDashboardMessage";
	}
	
	@PostMapping("/saveMessage")
	public String saveMessage(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		log.info(request.getParameter("feedbackMessage"));
		
		String feedback = request.getParameter("feedbackMessage");
		Integer ticketId = Integer.valueOf((String)session.getAttribute("ticketId"));
		log.info("integer id : "+ticketId);
		String choice = (String) session.getAttribute("choice");
		ticketObject.updateTicketInfo(ticketId, feedback, choice, curr_userid);
		supportObject.updateNoOfTickets(curr_userid);
		return"redirect:/supportDashboard";
	}
	
//	@PostMapping("/saveMessage")
//    public String saveMessage(HttpServletRequest request) {
//        // Save the message in the database using the TicketService
//		//ticketObject.updateMessage(ticketId, message);
//		String ticketId = request.getParameter("accept");
//		System.out.println("ticketid is : " + ticketId);
//        // Redirect to the same page or the dashboard page after saving the message
//        return "redirect:/supportDashboardMessage";
//    }
}