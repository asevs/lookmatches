package pl.lukaszg.lookmatches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.Room;
import pl.lukaszg.lookmatches.service.RoomService;

import java.util.List;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room/allRooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @RequestMapping(value = "/room/addRoom",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }
}
