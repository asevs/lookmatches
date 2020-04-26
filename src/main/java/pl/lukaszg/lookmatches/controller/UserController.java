package pl.lukaszg.lookmatches.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.User;
import pl.lukaszg.lookmatches.service.UserService;

import java.util.List;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user/profile")
    public User user(@AuthenticationPrincipal User principal) {
        return principal;
    }

    @GetMapping("/user/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/addBasicUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User basicRegister(@RequestBody User user) {
        userService.addBasicUser(user);
        return user;
    }

    @RequestMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user);
    }

    @RequestMapping(value = "/addUser/{userId}/team/{teamId}")
    public String addUserToTeamById(@PathVariable(value = "teamId", required = true) Long teamId, @PathVariable(value = "userId", required = true) Long userId) {
        if (userId != null && teamId != null) {
            return userService.addUserToTeamById(teamId, userId);
        } else return "teamId or userId is null";
    }

    @RequestMapping(value = "/addUser/{userId}/room/{roomId}")
    public String addUserToRoomById(@PathVariable(value = "roomId", required = true) Long roomId, @PathVariable(value = "userId", required = true) Long userId) {
        if (userId != null && roomId != null) {
            return userService.addUserToRoomById(roomId, userId);
        } else return "roomId or userId is null";
    }
}
