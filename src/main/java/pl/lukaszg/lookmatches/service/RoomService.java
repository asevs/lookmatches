package pl.lukaszg.lookmatches.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukaszg.lookmatches.model.Room;
import pl.lukaszg.lookmatches.model.RoomRepository;
import pl.lukaszg.lookmatches.model.RoomStatus;
import pl.lukaszg.lookmatches.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("roomService")
public class RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TeamService teamService;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room addRoom(Room room) {
        Date currentDate = new Date();
        room.setCreatedDate(currentDate);
        room.setRoomStatus(RoomStatus.OPEN);
        if (room.getTeamFirst() == null)
            room.setTeamFirst(teamService.createRandomTeam(room.getSlots(), room.getOwnerUser()));
        if (room.getTeamSecond() == null)
            room.setTeamSecond(teamService.createRandomTeam(room.getSlots(), room.getOwnerUser()));
        return roomRepository.save(room);
        // TODO: 25.04.2020 ADD OWNER to team
    }

    public Optional<Room> getTeamById(Long id) {
        return roomRepository.findById(id);
    }

    public String closeMatch(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent()) {
            if (room.get().getScoreTeamFirst() == 0 || room.get().getScoreTeamSecond() == 0)
                return "Set scores for all teams";
        }
        room.get().setRoomStatus(RoomStatus.CLOSED);
        roomRepository.save(room.get());
        return "Room closed";
    }


}
