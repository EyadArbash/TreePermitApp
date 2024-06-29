import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import treePermit.model.*;
import treePermit.enums.RequestStatus;
import treePermit.service.UserService;
import treePermit.repository.UserRepository;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;

@SpringBootTest
public class FailingTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testInvalidEmailRegistration() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("invalid-email");
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("password");
        User result = userService.registerNewUser(registrationDto);
        assertNull(result, "Registration should fail for invalid email");
    }

    @Test
    void testEmptyPasswordRegistration() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("valid@email.com");
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("");
        User result = userService.registerNewUser(registrationDto);
        assertNull(result, "Registration should fail for empty password");
    }

    @Test
    void testRequestWithInvalidDates() {
        Request request = new Request();
        request.setStartdatumVorhaben(LocalDate.now().plusDays(5));
        request.setEnddatumVorhaben(LocalDate.now());
        request.setStatus(RequestStatus.GENEHMIGT);
        assertNotEquals(RequestStatus.GENEHMIGT, request.getStatus(), "Request with invalid dates should not be approved");
    }

    @Test
    void testChangeStatusOfClosedRequest() {
        Request request = new Request();
        request.setStatus(RequestStatus.ABGELEHNT);
        request.setStatus(RequestStatus.GENEHMIGT);
        assertNotEquals(RequestStatus.GENEHMIGT, request.getStatus(), "Closed request status should not change");
    }

    @Test
    void testSendEmptyMessage() {
        Message message = new Message();
        message.setText("");
        assertNotNull(message.getText(), "Empty message should not be allowed");
    }

    @Test
    void testInvalidNotificationCreation() {
        Notification notification = new Notification(null);
        assertNotNull(notification.getMessage(), "Notification with null message should not be created");
    }

    @Test
    void testInvalidRequestCheckCreation() {
        RequestCheck requestCheck = new RequestCheck();
        requestCheck.setRequestId(null);
        assertNotNull(requestCheck.getRequestId(), "RequestCheck with null requestId should not be allowed");
    }

    @Test
    void testGetUserNameByInvalidEmail() {
        String result = userService.getUserNameByEmail("nonexistent@example.com");
        assertNotNull(result, "Should return a value for non-existent email");
    }

    @Test
    void testApproveRequestWithoutChecks() {
        RequestCheck requestCheck = new RequestCheck();
        requestCheck.setZustaendigkeit(false);
        requestCheck.setVollstaendigkeit(false);
        requestCheck.setVorhabenUmgesetzt(false);
        requestCheck.setVoraussetzungenErfuellt(false);
        assertTrue(requestCheck.isZustaendigkeit() && requestCheck.isVollstaendigkeit()
                        && requestCheck.isVorhabenUmgesetzt() && requestCheck.isVoraussetzungenErfuellt(),
                "Request should not be approved without all checks passing");
    }

    @Test
    void testDuplicateUsernameRegistration() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("test@email.com");
        registrationDto.setUsername("existingUser");
        registrationDto.setPassword("password");
        User result = userService.registerNewUser(registrationDto);
        assertNull(result, "Registration should fail for duplicate username");
    }
}