package treePermit.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/messages")
    @ResponseBody
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    
    @RequestMapping("/chat")
    public String chatPage() {
        return "chat";
    }
}


