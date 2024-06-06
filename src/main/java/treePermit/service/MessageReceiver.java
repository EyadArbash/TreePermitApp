package treePermit.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import treePermit.model.Message;

@Component
public class MessageReceiver {
	
    @JmsListener(destination = "TreePermit")
    public void receiveMessage(Message message) {
    }
}
