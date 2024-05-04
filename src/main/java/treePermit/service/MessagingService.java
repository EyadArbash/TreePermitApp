package treePermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import treePermit.model.Message;
import treePermit.repository.MessageRepository;

@Service
public class MessagingService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String sender,String resever ,String text) {
        // Nachricht in der Datenbank speichern
        Message message = new Message(sender,resever,text );
        messageRepository.save(message);

    }

    public List<Message> getMessagesForUser(String username) {
        // Alle Nachrichten für den Benutzer aus der Datenbank abrufen
        return messageRepository.findByReceiver(username);
    }
}
