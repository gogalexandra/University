package org.example.controller;

import dto.team.*;
import entities.Driver;
import org.springframework.web.bind.annotation.*;
import service.TeamService;

import java.util.List;

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

    @GetMapping("/teams/{id}")
    List<Driver> getDriversForTeam(@PathVariable Integer id) {
        return teamService.getDriversForTeam(id);
    }

    @PostMapping("/teams")
    void add(@RequestBody AddTeamDTO dto) {
        teamService.addTeam(dto);
    }

    @DeleteMapping("/teams/{id}")
    void delete(@PathVariable Integer id) {
        teamService.deleteTeam(id);
    }

    @PutMapping("/teams")
    void update(@RequestBody UpdateTeamDTO dto) {
        teamService.updateTeam(dto);
    }

    @PostMapping("/teams/add")
    void addDriver(@RequestParam Integer teamId, @RequestParam Integer driverId) {
        teamService.addDriverToTeam(teamId, driverId);
    }

    @PostMapping("/teams/remove")
    void removeDriver(@RequestParam Integer teamId, @RequestParam Integer driverId) {
        teamService.removeDriverFromTeam(teamId, driverId);
    }
}
