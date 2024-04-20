package treePermit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/dashboard_applicant")
	public String dashboardApplicant() {
		return "dashboard_applicant";
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
	
	@GetMapping("/communication_interface")
	public String communicationInterface() {
		return "communication_interface";
	}
}

