import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import treePermit.model.RegistrationDto;
import treePermit.model.User;
import treePermit.repository.UserRepository;
import treePermit.service.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterNewUser_Success() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("testUser");
        registrationDto.setPassword("password");
        registrationDto.setEmail("test@example.com");

        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(registrationDto.getPassword());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setRole("ROLE_USER");

        when(userRepository.findByUsername(registrationDto.getUsername())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User registeredUser = userService.registerNewUser(registrationDto);

        assertNotNull(registeredUser);
        assertEquals(newUser, registeredUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testRegisterNewUser_UserExists() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setUsername("existingUser");
        registrationDto.setPassword("password");
        registrationDto.setEmail("existing@example.com");

        User existingUser = new User();
        existingUser.setUsername(registrationDto.getUsername());
        existingUser.setPassword(registrationDto.getPassword());
        existingUser.setEmail(registrationDto.getEmail());
        existingUser.setRole("ROLE_USER");

        when(userRepository.findByUsername(registrationDto.getUsername())).thenReturn(existingUser);

        User registeredUser = userService.registerNewUser(registrationDto);

        assertNull(registeredUser);
        verify(userRepository, never()).save(any(User.class));
    }
}
