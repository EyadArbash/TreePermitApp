import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;

import treePermit.model.RegistrationDto;
import treePermit.model.User;
import treePermit.service.UserService;

@SpringBootTest(classes = treePermit.Application.class)
public class UserServiceTests {

    @Autowired
    UserService userService;

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

}