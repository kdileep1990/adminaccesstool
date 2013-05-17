package com.semanticbits.employee.logout;

import com.semanticbits.employee.Employee;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/logout.html")
public class LogoutController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String logout(@ModelAttribute(value = "user") Employee user,
			BindingResult result,HttpServletRequest request) {
		if (result.hasErrors()) {
			return "login";
		} 
		else {			
			request.setAttribute("msg", "you have been logged out");
			request.getSession(false).invalidate();
			
			return "login";
		}
	}
}
