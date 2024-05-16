package treePermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import treePermit.model.Message;

@Component
public class MessageReceiver {
	
    @Autowired
    private MessageService messageService;

    @JmsListener(destination = "TreePermit")
    public void receiveMessage(Message message) {
        System.out.println("Received message: " + message);
        messageService.saveMessage(message);
    }
}
