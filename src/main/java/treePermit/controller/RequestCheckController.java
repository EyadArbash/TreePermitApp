package treePermit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import treePermit.model.RequestCheck;
import treePermit.repository.RequestCheckRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/request_check")
public class RequestCheckController {

    @Autowired
    private RequestCheckRepository requestCheckRepository;

    @PostMapping("/process_request") // Endpunkt wird zu /request_check/process_request
    public String processRequest(@ModelAttribute RequestCheck requestCheck, Model model) {
    	requestCheckRepository.save(requestCheck);
        
        if (!requestCheck.isVollstaendigkeit() || !requestCheck.isZustaendigkeit() || !requestCheck.isVorhabenUmgesetzt() || !requestCheck.isVoraussetzungenErfuellt()) {
            model.addAttribute("message", "Der Antrag wurde abgelehnt.");
            return "request_status";
        }

        model.addAttribute("message", "Der Antrag wurde erfolgreich angenommen.");
        return "request_status";
    }
}
