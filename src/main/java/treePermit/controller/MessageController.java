package treePermit.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

import treePermit.model.Message;
import treePermit.service.MessageSender;
import treePermit.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageSender messageSender;

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody Message message) {
        message.setMessageDate(new Date());
        messageSender.sendMessage("TreePermit", message);
        return messageService.saveMessage(message);
    }
    
    @PostMapping("/sendServerMessage")
    @ResponseBody
    public Message sendServerMessage(@RequestBody Message message, HttpServletRequest request) {
        message.setSender("Server");
        message.setReceiver(request.getParameter("sender"));
        message.setMessageDate(new Date());
        messageSender.sendMessage("TreePermit", message);
        return messageService.saveMessage(message);
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getAllMessages(HttpServletRequest request) {
        String currentUser = request.getRemoteUser();
        return messageService.getMessagesForReceiver(currentUser);
    }
    
    @GetMapping("/allMessages")
    @ResponseBody
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    
    @GetMapping("/clientMessages")
    @ResponseBody
    public List<Message> getMessagesForClient(@RequestParam String client) {
        return messageService.getMessagesForClient(client);
    }
    
    @RequestMapping("/userChat")
    public String userChatPage() {
        return "userChat";
    }

    @RequestMapping("/serverChatForClient")
    public String serverChatPage() {
        return "serverChatForClient";
    }
}


