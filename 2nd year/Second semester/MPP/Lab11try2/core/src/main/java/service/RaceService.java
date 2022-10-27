package service;

import dto.driver.AddDriverDTO;
import dto.driver.UpdateDriverDTO;
import dto.race.AddRaceDTO;
import dto.race.UpdateRaceDTO;
import entities.Driver;
import entities.Race;
import entities.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RaceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getRaces() {
        return raceRepository.findAll();
    }

    public Race getById(Integer id) {
        return raceRepository.getRaceById(id);
    }

    public void addRace(AddRaceDTO dto) {
        raceRepository.save(new Race(dto));
    }

    public void deleteRace(Integer id) {
        raceRepository.deleteById(id);
    }

    @Transactional
    public void updateRace(UpdateRaceDTO dto) {
        raceRepository.save(new Race(dto));
    }
}
