package pl.lukaszg.lookmatches.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.lukaszg.lookmatches.model.Team;
import pl.lukaszg.lookmatches.model.TeamRepository;

import java.util.List;

@Service("teamService")
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team addTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }
}
