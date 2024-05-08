package treePermit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import treePermit.model.Request;
import treePermit.model.User;
import treePermit.repository.RequestRepository;
import treePermit.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class RequestController {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/my-requests")
	public String myRequests(Model model, Authentication authentication) {
		if (authentication == null
				|| !(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)) {
			throw new IllegalStateException("Kein Benutzer ist angemeldet.");
		}
		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();
		User currentUser = userRepository.findByEmail(springUser.getUsername());

		if (currentUser == null) {
			throw new IllegalStateException("Benutzerdaten konnten nicht geladen werden.");
		}
		model.addAttribute("requests", currentUser.getRequests());
		return "my-requests";
	}

	@PostMapping("/submit-application")
	public ModelAndView submitApplication(Authentication authentication,
			@RequestParam("familienname") String familienname, @RequestParam("vornamen") String vornamen,
			@RequestParam("strasse") String strasse, @RequestParam("hausnummer") String hausnummer,
			@RequestParam("plz") String plz, @RequestParam("ort") String ort, @RequestParam("telefon") String telefon,
			@RequestParam("email") String email, @RequestParam("art_gewaechs") String artGewaechs,

			@RequestParam("baum_ausserhalb_wald") Boolean baumAusserhalbWald,
			@RequestParam("baum_ausserhalb_kurzumtriebsplantage") Boolean baumAusserhalbKurzumtriebsplantage,
			@RequestParam("baum_ausserhalb_gaertnerisch_genutzte_flaeche") Boolean baumAusserhalbGaertnerischGenutzteFlaeche,

			@RequestParam("strasse_standort") String strasseStandort,
			@RequestParam("hausnummer_standort") String hausnummerStandort,
			@RequestParam("plz_standort") String plzStandort, @RequestParam("ort_standort") String ortStandort,
			@RequestParam("startdatum_vorhaben") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdatumVorhaben,
			@RequestParam("enddatum_vorhaben") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddatumVorhaben,
			@RequestParam("beschreibung_vorhaben") String beschreibungVorhaben) {

		if (authentication == null
				|| !(authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User)) {
			throw new IllegalStateException("Kein Benutzer ist angemeldet.");
		}

		Request newRequest = new Request(familienname, vornamen, strasse, hausnummer, plz, ort, telefon, email,
				artGewaechs, baumAusserhalbWald, baumAusserhalbKurzumtriebsplantage,
				baumAusserhalbGaertnerischGenutzteFlaeche, strasseStandort, hausnummerStandort, plzStandort,
				ortStandort, startdatumVorhaben, enddatumVorhaben, beschreibungVorhaben);

		org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User) authentication
				.getPrincipal();
		User currentUser = userRepository.findByEmail(springUser.getUsername());
		if (currentUser == null) {
			throw new IllegalStateException("Benutzerdaten konnten nicht geladen werden.");
		}
		newRequest.setUser(currentUser);
		requestRepository.save(newRequest);

		return new ModelAndView("redirect:/my-requests");
	}
	
	
	@GetMapping("/dashboard_clerks")
	public String getSachbearbeiterUebersicht(Model model) {
	    List<Request> allRequests = requestRepository.findAll();
	    model.addAttribute("allRequests", allRequests);
	    return "dashboard_clerks";
	}

	@GetMapping("/request/{id}")
	public String getRequestDetails(@PathVariable Long id, Model model) {
	    Request request = requestRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Antrag nicht gefunden"));
	    model.addAttribute("request", request);
	    return "request_details";
	}

	
}
