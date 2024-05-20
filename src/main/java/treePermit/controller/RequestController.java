package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

@Controller
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private RequestCheckRepository requestCheckRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/accept-request/{id}")
    public String acceptRequest(@PathVariable Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Antrag nicht gefunden"));
        request.setAccepted(true); // Setzen Sie den Wert auf true
        request.setRejected(false); // Stellen Sie sicher, dass der Wert auf false gesetzt wird
        requestRepository.save(request);
        return "redirect:/dashboard_clerks";
    }

    @PostMapping("/reject-request/{id}")
    public String rejectRequest(@PathVariable Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Antrag nicht gefunden"));
        request.setAccepted(false); // Setzen Sie den Wert auf false
        request.setRejected(true); // Setzen Sie den Wert auf true
        requestRepository.save(request);
        return "redirect:/dashboard_clerks";
    }

    @GetMapping("/dashboard_clerks")
    public String getSachbearbeiterUebersicht(Model model) {
        List<Request> allRequests = requestRepository.findAll();
        for (Request request : allRequests) {
            if (request.getAccepted() != null && request.getAccepted()) {
                request.setStatus("accepted");
            } else if (request.getRejected() != null && request.getRejected()) {
                request.setStatus("rejected");
            }
        }
        model.addAttribute("allRequests", allRequests);
        return "dashboard_clerks";
    }



    @GetMapping("/request/{id}")
    public String getRequestDetails(@PathVariable Long id, Model model) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Antrag nicht gefunden"));
        model.addAttribute("request", request);
        return "request_details";
    }

    @PostMapping("/process_request")
    public String processRequest(@ModelAttribute RequestCheck requestCheck, Model model) {
        requestCheckRepository.save(requestCheck);

        if (!requestCheck.isVollstaendigkeit() || !requestCheck.isZustaendigkeit() || !requestCheck.isVorhabenUmgesetzt() || !requestCheck.isVoraussetzungenErfuellt()) {
            model.addAttribute("message", "Der Antrag wurde abgelehnt.");
            return "request_status";
        }

        model.addAttribute("message", "Der Antrag wurde erfolgreich angenommen.");
        return "request_status";
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
