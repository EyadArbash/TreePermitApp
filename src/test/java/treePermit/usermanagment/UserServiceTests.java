package treePermit.usermanagment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.context.SpringBootTest;

import treePermit.model.RegistrationDto;
import treePermit.model.User;
import treePermit.service.UserService;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserService userService;

    @Test
    public void testRegisterNewUser() {
        // Neuen Nutzer erstellen mittels RegistrationDto
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testuser");
        registrationDto.setPassword("testpassword");
        registrationDto.setEmail("testUser@example.com");

        // Nutzer registrieren
        User newUser = userService.registerNewUser(registrationDto);

        // Überprüfen, ob Nutzer in der Datenbank gespeichert wurde
        assertNotNull(newUser);
        assertEquals("testUser", newUser.getUsername());
        assertEquals("testUser@example.com", newUser.getEmail());
        assertEquals("ROLE_USER", newUser.getRole());
    }

}
