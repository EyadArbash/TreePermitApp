package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import treePermit.enums.RequestStatus;

import treePermit.model.Request;
import treePermit.model.RequestCheck;
import treePermit.model.User;
import treePermit.repository.RequestCheckRepository;
import treePermit.repository.RequestRepository;
import treePermit.repository.UserRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;



@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestCheckRepository requestCheckRepository;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/request/{id}")
    public String getRequestDetails(@PathVariable Long id, Model model) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Antrag nicht gefunden"));
        model.addAttribute("request", request);
        return "request_details";
    }




    @PostMapping("/submit-application")
    public String submitApplication(Principal principal,
                                    @RequestParam("familienname") String familienname,
                                    @RequestParam("vornamen") String vornamen,
                                    @RequestParam("strasse") String strasse,
                                    @RequestParam("hausnummer") String hausnummer,
                                    @RequestParam("plz") String plz,
                                    @RequestParam("ort") String ort,
                                    @RequestParam("telefon") String telefon,
                                    @RequestParam("email") String email,
                                    @RequestParam("art_gewaechs") String artGewaechs,
                                    @RequestParam("baum_ausserhalb_wald") Boolean baumAusserhalbWald,
                                    @RequestParam("baum_ausserhalb_kurzumtriebsplantage") Boolean baumAusserhalbKurzumtriebsplantage,
                                    @RequestParam("baum_ausserhalb_gaertnerisch_genutzte_flaeche") Boolean baumAusserhalbGaertnerischGenutzteFlaeche,
                                    @RequestParam("strasse_standort") String strasseStandort,
                                    @RequestParam("hausnummer_standort") String hausnummerStandort,
                                    @RequestParam("plz_standort") String plzStandort,
                                    @RequestParam("ort_standort") String ortStandort,
                                    @RequestParam("startdatum_vorhaben") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdatumVorhaben,
                                    @RequestParam("enddatum_vorhaben") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddatumVorhaben,
                                    @RequestParam("beschreibung_vorhaben") String beschreibungVorhaben) {

        User currentUser = userRepository.findByEmail(principal.getName());
        if (currentUser == null) {
            throw new IllegalStateException("Benutzerdaten konnten nicht geladen werden.");
        }

        Request newRequest = new Request();
        newRequest.setUser(currentUser);
        requestRepository.save(newRequest);

        return "redirect:/my-requests";
    }
}
