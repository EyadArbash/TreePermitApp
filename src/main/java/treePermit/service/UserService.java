package treePermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import treePermit.model.RegistrationDto;
import treePermit.model.User;
import treePermit.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void initializeUsers() {
        createUser("user", "user", "ROLE_USER");
        createUser("clerk", "clerk", "ROLE_CLERK");
    }
    
    private void createUser(String username, String password, String role) {
        if (userRepository.findByUsername(username) == null) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(username + "@example.com");
            newUser.setPassword(password);
            newUser.setRole(role);
            userRepository.save(newUser);
        } else {
            System.out.println("User already exists: " + username);
        }
    }
    
    public User registerNewUser(RegistrationDto registrationDto) {
        User newUser = new User();
        newUser.setUsername(registrationDto.getUsername());
        newUser.setPassword(registrationDto.getPassword()); 
        newUser.setEmail(registrationDto.getEmail());
        newUser.setRole("ROLE_USER");
        return userRepository.save(newUser);
    }
    
}