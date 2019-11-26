package pl.lukaszg.lookmatches.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.User;
import pl.lukaszg.lookmatches.model.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping("/user")
    public User user(@AuthenticationPrincipal User principal) {
        return principal;
    }

    @GetMapping("/users")
    public ResponseEntity getUsers() throws JsonProcessingException {
        List<User> users = (List<User>) userRepository.findAll();
        return ResponseEntity.ok(objectMapper.writeValueAsString(users));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user){
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());

        if (userFromDb.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}
