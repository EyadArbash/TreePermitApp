import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import treePermit.model.Message;
import treePermit.repository.MessageRepository;
import treePermit.service.MessageService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageServiceTests {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMessage() {
        Message message = new Message();
        message.setText("Test message");
        when(messageRepository.save(any(Message.class))).thenReturn(message);

        Message savedMessage = messageService.saveMessage(message);

        assertEquals("Test message", savedMessage.getText());
        verify(messageRepository, times(1)).save(message);
    }

    @Test
    void testGetMessagesBetweenServerAndClient() {
        String clientEmail = "client@example.com";
        String serverUsername = "clerk";
        List<Message> expectedMessages = Arrays.asList(new Message(), new Message());

        when(messageRepository.findMessagesBetween(clientEmail, serverUsername)).thenReturn(expectedMessages);

        List<Message> actualMessages = messageService.getMessagesBetweenServerAndClient(clientEmail);

        assertEquals(expectedMessages, actualMessages);
        verify(messageRepository, times(1)).findMessagesBetween(clientEmail, serverUsername);
    }
}