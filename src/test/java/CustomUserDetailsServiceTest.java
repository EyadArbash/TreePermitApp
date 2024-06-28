import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import treePermit.model.User;
import treePermit.repository.UserRepository;
import treePermit.service.CustomUserDetailsService;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists() {
        User user = new User();
        user.setEmail("testuser@example.com");
        user.setPassword("password");
        user.setRole("ROLE_USER");

        when(userRepository.findByEmail("testuser@example.com")).thenReturn(user);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("testuser@example.com");

        assertEquals(user.getEmail(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(user.getRole())));
    }

    @Test
    void loadUserByUsername_UserDoesNotExist() {
        when(userRepository.findByEmail("testuser@example.com")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("testuser@example.com"));
    }
}