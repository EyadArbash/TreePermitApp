package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import treePermit.service.MessageSender;

@Controller
public class WebController {
	
	@Autowired
	private MessageSender messageSender;
	@GetMapping("/")
	public String login() {
		return "redirect:/login";
	}

	@GetMapping("/login")
	public ModelAndView getLoginWithError(@RequestParam(value = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (error != null) {
			modelAndView.addObject("error", "Invalid email or password");
		}
		return modelAndView;
	}

	@GetMapping("/dashboard_user")
	public String dashboardApplicant() {
		return "dashboard_user";
	}

	@GetMapping("/application_form")
	public String applicationForm() {
		return "application_form";
	}

	@GetMapping("/dashboard_clerks")
	public String dashboardClerks() {
		return "dashboard_clerks";
	}

	@GetMapping("/detail_view_clerks")
	public String detailViewClerks() {
		return "detail_view_clerks";
	}

//	@GetMapping("/communication_interface")
//	public String communicationInterface() {
//		return "communication_interface";
//	}
	
	@GetMapping("/communication_interface")
	public String sendMessage() {
	    messageSender.sendMessage("queueName", "Hello, JMS!");
	    return "communication_interface";
	}
}
