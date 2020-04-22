package pl.lukaszg.lookmatches.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lukaszg.lookmatches.model.Room;
import pl.lukaszg.lookmatches.model.RoomRepository;

import java.util.List;

@Service("roomService")
public class RoomService {

    @Autowired
    RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

}
