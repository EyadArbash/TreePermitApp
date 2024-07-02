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
import treePermit.service.MessageService;
import treePermit.service.UserService;
import treePermit.repository.RequestRepository;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/sendServerMessage")
    @ResponseBody
    public Message sendServerMessage(HttpServletRequest request,@RequestBody Message message,@RequestParam Long requestId) {
        Request myrequest = requestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        String currentUser = request.getRemoteUser();
        String serverUserName = userService.getUserNameByEmail(currentUser);
        message.setMessageDate(new Date());
        message.setSender(serverUserName);
        message.setReceiver(myrequest.getUser().getUsername());
        return messageService.saveMessage(message);
    }
    
    @PostMapping("/sendClientMessage")
    @ResponseBody
    public Message sendClientMessage(@RequestBody Message message,@RequestParam Long requestId) {
    	Request myrequest = requestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
    	message.setSender(myrequest.getUser().getUsername());
    	message.setMessageDate(new Date());
        message.setReceiver("clerk");
        return messageService.saveMessage(message);
    }
    
    @GetMapping("/clientMessages")
    @ResponseBody
    public List<Message> getMessagesForClient(HttpServletRequest request) {
        String currentUser = request.getRemoteUser();
        String clientUserName = userService.getUserNameByEmail(currentUser);
        List<Message> messages = messageService.getMessagesBetweenServerAndClient(clientUserName);
        return messages;
    }

    @GetMapping("/serverMessages")
    @ResponseBody
    public List<Message> getMessagesForServer(HttpServletRequest request,@RequestParam Long requestId) {
        Request myrequest = requestRepository.findById(requestId).orElseThrow(() -> new IllegalArgumentException("Invalid request ID"));
        String clientUserName = myrequest.getUser().getUsername();
        List<Message> messages = messageService.getMessagesBetweenServerAndClient(clientUserName);
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
