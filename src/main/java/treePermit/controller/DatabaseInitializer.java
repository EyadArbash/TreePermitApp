//package treePermit.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import treePermit.repository.UserRepository;
//import treePermit.model.User;
//@Component
//public class DatabaseInitializer implements CommandLineRunner {
//
//    @Autowired
//    private UserRepository repository;
//
//    @Override
//    public void run(String... args) {
//        User entity = new User();
//        entity.setUsername("Test Name");
//        repository.save(entity);
//        System.out.println("Test Entity saved!");
//    }
//}
