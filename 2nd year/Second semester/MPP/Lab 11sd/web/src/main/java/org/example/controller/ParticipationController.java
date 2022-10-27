package org.example.controller;

import dto.participation.AddParticipationDTO;
import dto.participation.GetParticipationsDTO;
import org.springframework.web.bind.annotation.*;
import service.ParticipationService;

@RestController
public class ParticipationController {
    private final ParticipationService participationService;

    public ParticipationController(ParticipationService participationService) {
        this.participationService = participationService;
    }

    @GetMapping("/participations")
    GetParticipationsDTO all(@RequestParam(required = false) Integer pointsGreaterThan) {
        if (pointsGreaterThan != null) {
            return new GetParticipationsDTO(participationService.getParticipationsWithPointsGreaterThan(pointsGreaterThan));
        }
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

}
