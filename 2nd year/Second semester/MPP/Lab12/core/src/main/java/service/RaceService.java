package service;

import dto.driver.AddDriverDTO;
import dto.driver.UpdateDriverDTO;
import dto.race.AddRaceDTO;
import dto.race.UpdateRaceDTO;
import entities.Driver;
import entities.Race;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.RaceRepository;

import java.util.List;

@Service
public class RaceService {
    private final RaceRepository raceRepository;

    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public List<Race> getRaces() {
        return raceRepository.findAll();
    }

    public void addRace(AddRaceDTO dto) {
        raceRepository.save(new Race(dto.getName()));
    }

    public void deleteRace(Integer id) {
        raceRepository.deleteById(id);
    }

    @Transactional
    public void updateRace(Integer id, UpdateRaceDTO dto) {
        final var race = raceRepository.findById(id).get();
        if (dto.getName() != null) {
            race.setName(dto.getName());
        }
    }
}
