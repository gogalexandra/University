package org.example.controller;

import dto.participation.AddParticipationDTO;
import dto.participation.GetParticipationsDTO;
import dto.race.GetRacesDTO;
import dto.team.GetTeamsDTO;
import entities.Participation;
import entities.Race;
import entities.Team;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import service.ParticipationService;

import java.util.List;

@RestController
public class ParticipationController {
    private final ParticipationService participationService;

    private static final Logger logger = LoggerFactory.getLogger(ParticipationController.class);

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping("/participations")
    GetParticipationsDTO all() {
        logger.info("entering /participations with GET");
        return new GetParticipationsDTO(participationService.getParticipations());
    }

    @PostMapping("/participations")
    void add(@RequestBody AddParticipationDTO dto) {
        participationService.addParticipation(dto);
    }

    @DeleteMapping("/participations/{id}")
    void delete(@PathVariable Integer id) {
        participationService.delete(id);
    }

    @GetMapping("/participations/races/{id}")
    GetRacesDTO getRacesForTeam(@PathVariable Integer id) {
        return new GetRacesDTO(participationService.getRacesForTeam(id));
    }

    @GetMapping("/participations/teams/{id}")
    GetTeamsDTO getTeamsForRace(@PathVariable Integer id) {
        return new GetTeamsDTO(participationService.getTeamsForRace(id));
    }
}
