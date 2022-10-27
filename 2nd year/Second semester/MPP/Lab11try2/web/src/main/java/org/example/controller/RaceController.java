package org.example.controller;

import dto.driver.AddDriverDTO;
import dto.driver.GetDriversDTO;
import dto.driver.UpdateDriverDTO;
import dto.race.AddRaceDTO;
import dto.race.GetRacesDTO;
import dto.race.UpdateRaceDTO;
import entities.Race;
import org.springframework.web.bind.annotation.*;
import service.RaceService;

import java.util.List;

@RestController
public class RaceController {
    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }


    @GetMapping("/races")
    GetRacesDTO all() {
        return new GetRacesDTO(raceService.getRaces());
    }

    @PostMapping("/races")
    void add(@RequestBody AddRaceDTO dto) {
        raceService.addRace(dto);
    }

    @DeleteMapping("/races/{id}")
    void delete(@PathVariable Integer id) {
        raceService.deleteRace(id);
    }

    @PutMapping("/races")
    void updateName(@RequestBody UpdateRaceDTO dto) {
        raceService.updateRace(dto);
    }
}
