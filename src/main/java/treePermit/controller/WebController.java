package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import treePermit.model.Request;
import treePermit.repository.RequestRepository;
import java.util.List;

@Controller
public class WebController {
	@Autowired
	private RequestRepository requestRepository;

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

	@GetMapping("/detail_view_clerks")
	public String detailViewClerks() {
		return "detail_view_clerks";
	}

	@GetMapping("/communication_interface")
	public String communicationInterface() {
		return "communication_interface";
	}




}
