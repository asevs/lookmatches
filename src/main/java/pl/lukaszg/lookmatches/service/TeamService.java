package pl.lukaszg.lookmatches.service;

import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lukaszg.lookmatches.model.Team;
import pl.lukaszg.lookmatches.model.TeamRepository;
import pl.lukaszg.lookmatches.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service("teamService")
public class TeamService {

    @Autowired
    TeamRepository teamRepository;


    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team addTeam(@RequestBody Team team) {
        if (!team.getName().equals(teamRepository.findByName(team.getName())))
            return teamRepository.save(team);
        else return null;
        // TODO: 25.04.2020 ADD OWNER to team 
    }

    public Optional<Team> getTeamById(int id) {
        return teamRepository.findById(id);
    }

    public String setTeamName(int id, String name) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isPresent()) {
            team.get().setName(name);
            return "Done";
        } else return "Bad team";
    }

    public Team createRandomTeam(int slots, User owner) {
        Team team = new Team();
        team.setName("#" + new Random().nextInt(100000));
        team.setAssists(0);
        team.setGoals(0);
        team.setSlots(slots);
        team.setOwner(owner);
        return teamRepository.save(team);
    }
}
