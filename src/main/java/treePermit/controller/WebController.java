package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import treePermit.repository.AntragRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {
	@Autowired
	private AntragRepository antragRepository;
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
	public String dashboardClerks(Model model) {
	    try {
	        // Lade alle Anträge mit dem Status "Wird bearbeitet" aus der Datenbank
	        List<Antrag> antraege = antragRepository.findByFamilienname("Wird bearbeitet");

	        // Füge die Anträge dem Modell hinzu, damit sie in der Ansicht verwendet werden können
	        model.addAttribute("antraege", antraege);

	        // Leite den Benutzer auf das Dashboard der Sachbearbeiter weiter
	        return "dashboard_clerks";  // Stelle sicher, dass "dashboard_clerks" dem Namen deiner HTML-Datei entspricht
	    } catch (Exception e) {
	        e.printStackTrace();  // Dies gibt den Fehler auf der Konsole aus, um eine bessere Diagnose zu ermöglichen
	        return "error";  // Leite zu einer allgemeinen Fehlerseite um
	    }
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
	public String myRequests(Model model, Principal principal) {
	    // Lade Anträge des aktuellen Benutzers aus der Datenbank
	    String username = principal.getName(); // Benutzername des aktuellen Benutzers
	    List<Antrag> antraege = antragRepository.findByFamilienname(username);
	    
	    // Füge die Anträge dem Modell hinzu, damit sie in der Ansicht verwendet werden können
	    model.addAttribute("antraege", antraege);

	    // Leite den Benutzer zur HTML-Seite "Meine Anträge" weiter
	    return "my-requests";  // Stelle sicher, dass "my-requests" dem Namen deiner HTML-Datei entspricht
	}


	
	 @PostMapping("/submit-application")
	 public ModelAndView submitApplication(@RequestParam("familienname") String familienname,
	                                                   @RequestParam("vornamen") String vornamen,
	                                                   @RequestParam("straße") String straße,
	                                                   @RequestParam("hausnummer") String hausnummer,
	                                                   @RequestParam("plz") String plz,
	                                                   @RequestParam("ort") String ort,
	                                                   @RequestParam("email") String email,
	                                                   @RequestParam("art_gewaechs") String artGewaechs,
	                                                   @RequestParam("straße_standort") String straßeStandort,
	                                                   @RequestParam("hausnummer_standort") String hausnummerStandort,
	                                                   @RequestParam("plz_standort") String plzStandort,
	                                                   @RequestParam("ort_standort") String ortStandort,
	                                                   @RequestParam("startdatum_vorhaben") String startdatumVorhaben,
	                                                   @RequestParam("enddatum_vorhaben") String enddatumVorhaben,
	                                                   @RequestParam("beschreibung_vorhaben") String beschreibungVorhaben) {
	     // Erstelle ein neues Antrag-Objekt
	     Antrag newAntrag = new Antrag(familienname, vornamen, straße, hausnummer, plz, ort, email, artGewaechs,
	                                    straßeStandort, hausnummerStandort, plzStandort, ortStandort,
	                                    startdatumVorhaben, enddatumVorhaben, beschreibungVorhaben);
	    // newAntrag.setHausnummer(UUID.randomUUID().toString()); // Setzt eine einzigartige Nummer für den Antrag

	     // Speichere den neuen Antrag in der Datenbank über das Repository
	     antragRepository.save(newAntrag);

	     // Weiterleitung auf die Seite "Meine Anträge"
	     return new ModelAndView("redirect:/my-requests");
	 }


	
}
