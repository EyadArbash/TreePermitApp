package treePermit.service;
import treePermit.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import treePermit.model.Message;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    
    public List<Message> getMessagesForReceiver(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    public List<Message> getMessagesForClient(String client) {
        return messageRepository.findBySenderOrReceiver(client, "Server");
    }
    
}
