package treePermit.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import treePermit.model.Message;
import treePermit.model.Request;
import treePermit.service.MessageSender;
import treePermit.service.MessageService;
import treePermit.repository.RequestRepository;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody Message message) {
        message.setMessageDate(new Date());
        message.setReceiver("clerk@example.com");
        messageSender.sendMessage("TreePermit", message);
        return messageService.saveMessage(message);
    }

    @PostMapping("/sendServerMessage")
    @ResponseBody
    public Message sendServerMessage(@RequestBody Message message, @RequestParam Long requestId) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        message.setMessageDate(new Date());
        message.setSender("clerk@example.com");
        message.setReceiver(request.getEmail());
        messageSender.sendMessage("TreePermit", message);
        return messageService.saveMessage(message);
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getMessagesForCurrentUser(HttpServletRequest request) {
        String currentUser = request.getRemoteUser();
        if (currentUser == null) {
            throw new IllegalStateException("Kein Benutzer ist angemeldet.");
        }
        return messageService.getMessagesBetweenServerAndClient(currentUser);
    }

    @GetMapping("/clientMessages")
    @ResponseBody
    public List<Message> getMessagesForClient(@RequestParam Long requestId) {
        Request request = requestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        String clientEmail = request.getEmail();
        String serverEmail = "clerk@example.com";
        List<Message> messages = messageService.getMessagesBetweenServerAndClient(clientEmail);
        if (messages.isEmpty())
            System.out.println("No messages found between " + clientEmail + " and " + serverEmail);
        return messages;
    }

    @RequestMapping("/client_chat")
    public String userChatPage() {
        return "client_chat";
    }

    @RequestMapping("/server_chat")
    public String serverChatPage() {
        return "server_chat";
    }
}
