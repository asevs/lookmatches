package pl.lukaszg.lookmatches.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukaszg.lookmatches.model.Room;
import pl.lukaszg.lookmatches.model.RoomRepository;
import pl.lukaszg.lookmatches.model.RoomStatus;
import pl.lukaszg.lookmatches.model.User;

import javax.transaction.Transactional;
import java.util.*;

@Service("roomService")
public class RoomService {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;

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
    @Transactional
    public String randomizeTeams(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent() && !room.get().getUsers().isEmpty()) {
            List<User> teamFirst = new ArrayList<>();
            teamFirst.addAll(room.get().getUsers());
            List<User> teamSecond = new ArrayList<>();
            int usersListSize = room.get().getUsers().size();
            Collections.shuffle(teamFirst, new Random(5));
            for (int i = 0; i < usersListSize / 2; i++) {
                teamSecond.add(teamFirst.get(i));
                teamFirst.remove(i);
            }
            if (!teamFirst.isEmpty() && !teamSecond.isEmpty()) {
                for (int i = 0; i < teamFirst.size(); i++) {
                    userService.addUserToTeamById(room.get().getTeamFirst().getId(), teamFirst.get(i).getId());
                    userService.deleteUserById(teamFirst.get(i).getId());
                }
                for (int i = 0; i < teamSecond.size(); i++) {
                    userService.addUserToTeamById(room.get().getTeamSecond().getId(), teamSecond.get(i).getId());
                    userService.deleteUserById(teamSecond.get(i).getId());
                }

                return "Randomize Teams OK";
            } else return "Teams are empty";
        } else return "Room or users is null/empty";
    }


}
