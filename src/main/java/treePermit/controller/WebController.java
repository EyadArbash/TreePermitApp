package treePermit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

 

    @GetMapping("/register")
    public String register() {
        System.out.println("Register-Methode aufgerufen!");
        return "register";
    }
    

}
