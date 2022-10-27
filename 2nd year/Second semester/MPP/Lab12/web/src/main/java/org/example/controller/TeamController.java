package org.example.controller;

import dto.race.GetRacesDTO;
import dto.team.*;
import org.springframework.web.bind.annotation.*;
import service.TeamService;

import java.util.stream.Collectors;

@RestController
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @GetMapping("/teams")
    GetTeamsDTO all() {
        return new GetTeamsDTO(teamService.getTeams());
    }

    @GetMapping("/teams/memberCount")
    GetTeamsMemberCountDTO allMemberCount() {
        final var teams = teamService
            .getTeams()
            .stream()
            .map(team -> new GetTeamsMemberCountDTO.InnerTeam(
                team.getId(),
                team.getName(),
                team.getDrivers().size()
            ))
            .collect(Collectors.toList());
        return new GetTeamsMemberCountDTO(teams);
    }

    @PostMapping("/teams")
    void add(@RequestBody AddTeamDTO dto) {
        teamService.addTeam(dto);
    }

    @DeleteMapping("/teams/{id}")
    void delete(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }

    @PutMapping("/teams/{id}")
    void updateName(@PathVariable Integer id, @RequestBody UpdateTeamDTO dto) {
        teamService.updateTeam(id, dto);
    }

    @PostMapping("/teams/{id}/drivers")
    void addDriver(@PathVariable Integer id, @RequestBody AddDriverToTeamDTO dto) {
        teamService.addDriverToTeam(id, dto);
    }
}
