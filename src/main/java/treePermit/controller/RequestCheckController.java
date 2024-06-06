package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import treePermit.enums.RequestStatus;
import treePermit.model.Notification;
import treePermit.model.Request;
import treePermit.model.RequestCheck;
import treePermit.repository.RequestCheckRepository;
import treePermit.repository.RequestRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/request_check")
public class RequestCheckController {

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/process_request") // Endpunkt wird zu /request_check/process_request
    public String processRequest(@ModelAttribute RequestCheck requestCheck, Model model) {
        Request request = requestRepository.findById(requestCheck.getRequestId())
                         .orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));

        StringBuilder rejectionReason = new StringBuilder();

        if (!requestCheck.isZustaendigkeit()) {
            rejectionReason.append("Zuständigkeit nicht gegeben. ");
        }
        if (!requestCheck.isVollstaendigkeit()) {
            rejectionReason.append("Die Unterlagen sind unvollständig. ");
        }
        if (!requestCheck.isVorhabenUmgesetzt()) {
            rejectionReason.append("Das Vorhaben wurde bereits umgesetzt. ");
        }
        if (!requestCheck.isVoraussetzungenErfuellt()) {
            rejectionReason.append("Die Voraussetzungen sind nicht erfüllt. ");
        }

        if (rejectionReason.length() > 0) {
            request.setStatus(RequestStatus.ABGELEHNT);
            model.addAttribute("message", "Der Antrag wurde abgelehnt: " + rejectionReason.toString());
            request.setNotification(new Notification("Der Antrag wurde abgelehnt: " + rejectionReason.toString()));
        } else {
            request.setStatus(RequestStatus.GENEHMIGT);
            model.addAttribute("message", "Der Antrag wurde erfolgreich angenommen.");
            request.setNotification(new Notification("Der Antrag wurde erfolgreich angenommen."));
        }

        requestRepository.save(request);
        return "redirect:/dashboard_clerks";
    }


}
