import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import treePermit.controller.RequestCheckController;
import treePermit.model.Request;
import treePermit.model.RequestCheck;
import treePermit.model.Notification;
import treePermit.repository.RequestRepository;
import treePermit.enums.RequestStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RequestCheckControllerTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private Model model;

    @InjectMocks
    private RequestCheckController requestCheckController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessRequest_Approved() {
        RequestCheck requestCheck = new RequestCheck();
        requestCheck.setRequestId(1L);
        requestCheck.setZustaendigkeit(true);
        requestCheck.setVollstaendigkeit(true);
        requestCheck.setVorhabenUmgesetzt(true);
        requestCheck.setVoraussetzungenErfuellt(true);

        Request mockRequest = new Request();
        when(requestRepository.findById(1L)).thenReturn(Optional.of(mockRequest));

        String result = requestCheckController.processRequest(requestCheck, model);

        assertEquals("redirect:/dashboard_clerks", result);
        assertEquals(RequestStatus.GENEHMIGT, mockRequest.getStatus());
        assertNotNull(mockRequest.getNotification());
        assertEquals("Der Antrag wurde erfolgreich angenommen.", mockRequest.getNotification().getMessage());
        verify(requestRepository).save(mockRequest);
    }

    @Test
    void testProcessRequest_Rejected() {
        RequestCheck requestCheck = new RequestCheck();
        requestCheck.setRequestId(1L);
        requestCheck.setZustaendigkeit(false);
        requestCheck.setVollstaendigkeit(true);
        requestCheck.setVorhabenUmgesetzt(true);
        requestCheck.setVoraussetzungenErfuellt(true);

        Request mockRequest = new Request();
        when(requestRepository.findById(1L)).thenReturn(Optional.of(mockRequest));

        String result = requestCheckController.processRequest(requestCheck, model);

        assertEquals("redirect:/dashboard_clerks", result);
        assertEquals(RequestStatus.ABGELEHNT, mockRequest.getStatus());
        assertNotNull(mockRequest.getNotification());
        assertTrue(mockRequest.getNotification().getMessage().contains("Der Antrag wurde abgelehnt"));
        assertTrue(mockRequest.getNotification().getMessage().contains("Zuständigkeit nicht gegeben"));
        verify(requestRepository).save(mockRequest);
    }

    @Test
    void testProcessRequest_InvalidRequestId() {
        RequestCheck requestCheck = new RequestCheck();
        requestCheck.setRequestId(1L);

        when(requestRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            requestCheckController.processRequest(requestCheck, model);
        });
    }
}