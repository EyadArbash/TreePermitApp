package treePermit.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import treePermit.service.MessagingService;

@RestController
public class MessageController {

    @Autowired
    private MessagingService messagingService;

    @PostMapping("/send-message")
    public ResponseEntity<?> sendMessage(@RequestBody MessageRequest messageRequest) {
        try {
            // Benutzernamen und Nachrichtentext aus der Anfrage erhalten
            String sender = messageRequest.getSender();
            String resever = messageRequest.getReceiver();
            String text = messageRequest.getText();
           
            // Nachricht senden und in der Datenbank speichern
            messagingService.sendMessage(sender,resever,text);
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}


