package uk.co.creative74.springmvchibernate.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import uk.co.creative74.springmvchibernate.model.User;

@Controller
public class SecurityController {
	
	static final Logger appLog = LoggerFactory.getLogger("application-log");
	
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public String listEmployees(ModelMap model) {

		model.addAttribute("title", "Spring Security + Hibernate Example");
		model.addAttribute("message", "This is default page!");
		
		return "admin-login/default";

	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		
		appLog.debug("GET /admin**");

		model.addAttribute("title", "Spring Security + Hibernate Example");
		model.addAttribute("message", "This page is for ROLE_ADMIN only!");

		return "admin-login/admin";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(	@RequestParam(value = "error", required = false) String error,
							@RequestParam(value = "logout", required = false) String logout,
							ModelMap model,
							HttpServletRequest request) {
		
		appLog.debug("GET /login");

		if (error != null) {
			appLog.debug("error : " + getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
			model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			appLog.debug("You've been logged out.");
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		
		User user = new User();
		model.addAttribute("user", user);
		
		return "admin-login/login";

	}
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}
}
