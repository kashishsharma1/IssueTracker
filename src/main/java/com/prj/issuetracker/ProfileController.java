package com.prj.issuetracker;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prj.issuetracker.model.EmpInfo;
import com.prj.issuetracker.service.EmployeeService;

@Controller
public class ProfileController {
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");

	@Autowired
	EmployeeService empObject;
	
	@GetMapping("/profile")
	public String showProfileDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		request.setAttribute("userDetails", curr_emp);
		request.setAttribute("username", curr_emp.getName());
		if(session.getAttribute("changepassword")!=null) {
			request.setAttribute("changepassword", session.getAttribute("changepassword"));
		}
		else {
			request.setAttribute("changepassword", null);
		}
		if(curr_emp.getRole().equals("support")) {
			return "editProfileSupport";
		}
		return "editProfileEmployee";
	}
	
	@PostMapping("/updateProfile")
	public String updateProfileDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		String parameter = request.getParameter("phone");
		Long updatedPhone = Long.parseLong(parameter);
		Integer curr_userid = (Integer) session.getAttribute("userid");
		empObject.updatePhone(curr_userid, updatedPhone);
		return "redirect:/profile";
	}
	
}
