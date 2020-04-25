package pl.lukaszg.lookmatches.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lukaszg.lookmatches.model.Team;
import pl.lukaszg.lookmatches.service.TeamService;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/api")
@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/team/allTeams")
    public List<Team> getTeams() {
        return teamService.getTeams();
    }

    @RequestMapping(value = "/team/addTeam",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

    @RequestMapping(value = "/team/{id}")
    public Optional<Team> findById(@PathVariable(value = "id", required = true) int id) {
        return teamService.getTeamById(id);
    }

    @RequestMapping(value = "/team/{id}/{name}")
    public String changeTeamName(@PathVariable(value = "id", required = true) int id, @PathVariable(value = "name", required = true) String name) {
        return teamService.setTeamName(id, name);
    }

}
