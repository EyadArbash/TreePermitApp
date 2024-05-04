package treePermit.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(destination = "TreePermit")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
