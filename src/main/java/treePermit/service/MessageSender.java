package treePermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import treePermit.model.Message;

@Service
public class MessageSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, Message message) {
        jmsTemplate.convertAndSend(destination, message);
    }

}
