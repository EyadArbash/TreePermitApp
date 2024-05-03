package treePermit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

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
	public String dashboardClerks(Model model) {
	    try {
	        List< Antrag> antraege = new ArrayList<>(DataStore.antraege.values());
	model.addAttribute("antraege", antraege);
	} catch (Exception e) {
	e.printStackTrace(); // Dies gibt den Fehler auf der Konsole aus, um eine bessere Diagnose zu ermöglichen
	return "error"; // Leite zu einer allgemeinen Fehlerseite um
	}

	
	// Leite den Benutzer auf das Dashboard der Sachbearbeiter weiter
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
	
	@GetMapping("/my-requests")
	public String myRequests(Model model) {
	    // Lade alle Anträge aus der globalen Map
	    List <Antrag> antraege = new ArrayList<>(DataStore.antraege.values());
	 // Füge die Anträge dem Model hinzu, damit sie in der Ansicht verwendet werden können
	    model.addAttribute("antraege", antraege);

	    // Leite den Benutzer zur HTML-Seite "Meine Anträge" weiter
	    return "my-requests";
	}

	
	@PostMapping("/submit-application")
	public ModelAndView submitApplication(@RequestParam("vorname") String vorname,
	                                      @RequestParam("nachname") String nachname,
	                                      @RequestParam("adresse") String adresse,
	                                      @RequestParam("stadt") String stadt,
	                                      @RequestParam("plz") String plz) {
	    // Erstelle ein neues Antrag-Objekt
	    Antrag newAntrag = new Antrag(vorname, nachname, stadt, "Wird bearbeitet");
	    // Verwende eine einfache UUID als Schlüssel für die Speicherung in der globalen Map
	    String key = UUID.randomUUID().toString();
	    DataStore.antraege.put(key, newAntrag);

	    // Weiterleitung auf die Seite "Meine Anträge"
	    return new ModelAndView("redirect:/my-requests");
	}
}
