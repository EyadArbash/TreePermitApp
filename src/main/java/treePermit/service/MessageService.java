package treePermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import treePermit.model.Message;
import treePermit.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenServerAndClient(String clientEmail) {
        String serverEmail = "clerk@example.com";
        return messageRepository.findMessagesBetween(clientEmail, serverEmail);
    }
}
