package com.example.demo;

import com.example.demo.models.Address;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SimpleController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<User>> getUsers() {
        List<User> list = userRepository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> createUser() {
        User user = new User();
        user.setName("Alex");
        user.setAddress(new HashSet<>());

        Address address = new Address();
        address.setCity("LA");
        address.setBuilding(12);
        address.setStreet("Nemo");
        address.setUser(user);

        Address address2 = new Address();
        address2.setCity("NY");
        address2.setBuilding(42);
        address2.setStreet("Green street");
        address2.setUser(user);

        user.getAddress().add(address);
        user.getAddress().add(address2);

        return ResponseEntity.ok(userRepository.save(user).getId());
    }

}
