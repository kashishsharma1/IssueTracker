package com.prj.issuetracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
	private static Logger log = LoggerFactory.getLogger("com.prj.issuetracker");
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false); 
        if (session != null) {
            // Invalidate the session to destroy it
            session.invalidate();
        }
        return "redirect:/login";
	}

}
