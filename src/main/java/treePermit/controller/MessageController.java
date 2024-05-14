package treePermit.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import treePermit.model.Message;

import treePermit.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    @ResponseBody
    public Message sendMessage(@RequestBody Message message) {
        message.setMessageDate(new Date());
        return messageService.saveMessage(message);
    }

    @GetMapping("/messages")
    public List<Message> getAllMessages() {
//	    messageSender.sendMessage("TreePermit", "Hello, JMS!");
        return messageService.getAllMessages();
    }
    
//    @GetMapping("/messages")
//    public ResponseEntity<List<Message>> getAllMessages() {
//        List<Message> messages = messageService.getAllMessages();
//        return ResponseEntity.ok(messages);
//    }

}


