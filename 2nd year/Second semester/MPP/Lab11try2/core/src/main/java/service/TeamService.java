package service;

import dto.race.UpdateRaceDTO;
import dto.team.AddDriverToTeamDTO;
import dto.team.AddTeamDTO;
import dto.team.TeamDTO;
import dto.team.UpdateTeamDTO;
import entities.Driver;
import entities.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DriverRepository;
import repository.TeamRepository;

import java.util.List;
import java.util.Objects;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final DriverRepository driverRepository;

    public TeamService(TeamRepository teamRepository, DriverRepository driverRepository) {
        this.teamRepository = teamRepository;
        this.driverRepository = driverRepository;
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    public Team getById(Integer id) {
        return teamRepository.getTeamById(id);
    }

    public void addTeam(AddTeamDTO dto) {
        teamRepository.save(new Team(dto));
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public void updateTeam(UpdateTeamDTO dto) {
        teamRepository.save(new Team(dto));
    }

    @Transactional
    public void addDriverToTeam(Integer teamId, Integer driverId) {
        final var driver = driverRepository.findDriverById(driverId);
        driver.setTeam(teamId);
        driverRepository.save(driver);
    }

    @Transactional
    public void removeDriverFromTeam(Integer teamId, Integer driverId) {
        final var driver = driverRepository.findDriverById(driverId);
        driver.setTeam(0);
        driverRepository.save(driver);
    }

    public List<Driver> getDriversForTeam(Integer teamId) {
        return driverRepository.findAllByTeam(teamId);
    }
}
