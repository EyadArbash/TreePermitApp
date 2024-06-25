import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import treePermit.model.RegistrationDto;
import treePermit.model.User;
import treePermit.repository.UserRepository;
import treePermit.service.UserService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = treePermit.Application.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        User user = new User();
        user.setUsername("testuser");
        userService.save(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterNewUser() {
        // Neuen Nutzer erstellen mittels RegistrationDto
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("testpassword");
        registrationDto.setEmail("testuser@example.com");

        // Überprüfen, ob der Benutzer bereits existiert
        String existingUsername = userService.getUserNameByEmail(registrationDto.getEmail());

        if (existingUsername != null) {
            // Wenn der Benutzer bereits existiert, ist der Test erfolgreich
            assertEquals("testuser", existingUsername);
        } else {
            // Nutzer registrieren
            User newUser = userService.registerNewUser(registrationDto);

            // Überprüfen, ob Nutzer in der Datenbank gespeichert wurde
            assertNotNull(newUser);
            assertEquals("testuser", newUser.getUsername());
            assertEquals("testuser@example.com", newUser.getEmail());
            assertEquals("ROLE_USER", newUser.getRole());
        }
    }

    @Test
    void testGetUserNameByEmail() {
        String email = "existinguser@example.com";
        User user = new User();
        user.setUsername("existinguser");
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(user);

        String result = userService.getUserNameByEmail(email);

        assertEquals("existinguser", result);
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserNameByEmailNotFound() {
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(null);

        String result = userService.getUserNameByEmail(email);

        assertNull(result);
        verify(userRepository, times(1)).findByEmail(email);
    }
    
}