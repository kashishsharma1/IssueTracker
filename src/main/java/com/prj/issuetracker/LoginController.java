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
public class LoginController {

	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");

	@Autowired
	EmployeeService empObject;
	
	@GetMapping("/")
	public String start() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam(name = "userid", required = false, defaultValue = "0") Integer userid, 
			@RequestParam(name = "password", required = false, defaultValue = "0") String password,
			ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		EmpInfo curr_emp = empObject.getEmployee(userid);
		log.info("current user "+ curr_emp);
		
		if(curr_emp!=null && curr_emp.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			session.setAttribute("changepassword", null);
			if(curr_emp.getRole().equals("employee")) {
				System.out.println("you are logged in");
				return "redirect:/employee";
			}
			else {
				return "redirect:/supportDashboard";
			}
		}
		else {
			model.addAttribute("error", "Username or password is incorrect...");
			return "login";
		}
	}

	@GetMapping("/forgotpassword")
	public String showForgotPassword() {
		return "forgotpassword";
	}

	@PostMapping("/forgotpassword")
	public String showForgotPassword(@RequestParam(name = "userid", required = false, defaultValue = "0") Integer userid,
			@RequestParam(name = "newpassword", required = false, defaultValue = "0") String newpassword, 
			@RequestParam(name = "cnfpassword", required = false, defaultValue = "0") String cnfpassword,
			ModelMap model)
	{	

		EmpInfo curr_emp = empObject.getEmployee(userid);
		if(curr_emp!=null && newpassword.equals(cnfpassword)) {
			empObject.updatePassword(userid, cnfpassword);
			return "login";
		}
		else {
			model.put("forgeterror", "username not found or the passwords didn't match");
			return "forgotpassword";
		}
	}
	
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam(name = "oldPassword", required = false, defaultValue = "0") String oldpassword,
			@RequestParam(name = "newPassword", required = false, defaultValue = "0") String newpassword, 
			@RequestParam(name = "cnfPassword", required = false, defaultValue = "0") String cnfpassword,
			ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("userid")==null) {
			return "redirect:/login";
		}
		Integer curr_userid = (Integer) session.getAttribute("userid");
		EmpInfo curr_emp = empObject.getEmployee(curr_userid);
		session.setAttribute("changepassword", null);
		if(curr_emp.getPassword().equals(oldpassword) && newpassword.equals(cnfpassword)) {
			session.setAttribute("changepassword", null);
			empObject.updatePassword(curr_userid, cnfpassword);
			return "redirect:/profile";
		}
		else {
			session.setAttribute("changepassword", "username not found or the passwords didn't match");
			return "redirect:/profile";
		}
	}

}
	
