package server.service;

import common.domain.Driver;
import common.domain.Participation;
import common.domain.Team;
import server.repository.DBpersistence.DriverDBRepository;
import server.repository.DBpersistence.ParticipationDBRepository;
import server.repository.DBpersistence.Repository;

import java.util.ArrayList;

public class TeamService {
    private Repository<Integer, Team> teamRepository;
    private Repository<Integer, Participation> participationRepository;
    private Repository<Integer, Driver> driverRepository;

    public TeamService(Repository<Integer, Team> teamRepository, Repository<Integer, Participation> participationRepository, Repository<Integer, Driver> driverRepository) {
        this.teamRepository = teamRepository;
        this.participationRepository = participationRepository;
        this.driverRepository = driverRepository;
    }

    public Iterable<Team> getTeams() {
        return teamRepository.findAll();
    }

    public void addTeam(Integer id, String name, Integer teamPrincipalId) {
        Team team = new Team(id, name, teamPrincipalId);
        this.teamRepository.save(team);
    }

    public void deleteTeam(Integer id) {
        this.teamRepository.delete(id);
    }


    public void updateTeam(Integer id, String name, Integer teamPrincipalId) {
        Team team = new Team(id, name, teamPrincipalId);
        this.teamRepository.update(team);
    }

    public Iterable<Participation> getTeamParticipations(Integer id) {
        Iterable<Participation> participations = new ArrayList<>();
        if(participationRepository instanceof ParticipationDBRepository){
            participations = ((ParticipationDBRepository) participationRepository).getParticipationRace(id);
        }
        return participations;
    }

    public Iterable<Driver> getTeamDrivers(Integer id) {
        Iterable<Driver> drivers = new ArrayList<>();
        if(driverRepository instanceof DriverDBRepository){
            drivers = ((DriverDBRepository) driverRepository).getTeamDrivers(id);
        }
        return drivers;
    }
}