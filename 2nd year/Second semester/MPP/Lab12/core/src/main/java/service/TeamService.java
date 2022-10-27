package service;

import dto.team.AddDriverToTeamDTO;
import dto.team.AddTeamDTO;
import dto.team.UpdateTeamDTO;
import entities.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DriverRepository;
import repository.TeamRepository;

import java.util.List;

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

    public void addTeam(AddTeamDTO dto) {
        teamRepository.save(new Team(dto.getName()));
    }

    public void deleteTeam(Integer id) {
        teamRepository.deleteById(id);
    }

    @Transactional
    public void updateTeam(Integer id, UpdateTeamDTO dto) {
        final var team = teamRepository.findById(id).get();
        if (dto.getName() != null) {
            team.setName(dto.getName());
        }
    }

    @Transactional
    public void addDriverToTeam(Integer id, AddDriverToTeamDTO dto) {
        final var driver = driverRepository.findById(dto.getId()).get();
        final var team = teamRepository.findById(id).get();
        driver.setTeam(team);
    }
}
