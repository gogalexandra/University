package org.example.service;

import dto.driver.AddDriverDTO;
import dto.driver.GetDriversDTO;
import dto.driver.UpdateDriverDTO;
import dto.participation.AddParticipationDTO;
import dto.participation.GetParticipationsDTO;
import dto.race.AddRaceDTO;
import dto.race.GetRacesDTO;
import dto.race.UpdateRaceDTO;
import dto.team.*;
import entities.Driver;
import entities.Participation;
import entities.Race;
import entities.Team;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
public class Service {

    static String base = "http://localhost:8080/api";
    final RestTemplate rest = new RestTemplate();

//    DRIVER
    public List<Driver> getDrivers() {
        final var result = rest.getForObject(base + "/drivers", GetDriversDTO.class);
        return result.getDrivers();
    }

    public List<Driver> getDriversForTeam(Integer teamId) {
        final var result = rest.getForObject(base + "/drivers/team/" + teamId, GetDriversDTO.class);
        return result.getDrivers();
    }

    public List<Driver> getDriversAlphabetically() {
        final var result = rest.getForObject(base + "/drivers/alphabetically", GetDriversDTO.class);
        return result.getDrivers();
    }

    public void addDriver(AddDriverDTO dto) {
        rest.postForObject(base + "/drivers", dto, AddDriverDTO.class);
    }

    public void deleteDriver(Integer id) {
        rest.delete(base + "/drivers/{id}", id);
    }

    public void updateDriver(Driver updateDriverDTO) {
        rest.put(base + "/drivers", updateDriverDTO, Driver.class);
    }

    public void addDriverToTeam(Integer teamId, Integer driverId) {
//        rest.postForObject(base + "/teams/add", new HttpEntity<>(null), HttpEntity.class, teamId, driverId);
        rest.postForObject(base + "/teams/add?teamId=" + teamId + "&driverId=" + driverId, null, Object.class);
    }

    public void removeDriverFromTeam(Integer teamId, Integer driverId) {
        rest.postForObject(base + "/teams/remove?teamId=" + teamId + "&driverId=" + driverId,
                null, Object.class);
    }


//    TEAM

    public List<Team> getTeams() {
        final var result = rest.getForObject(base + "/teams", GetTeamsDTO.class);
        return result.getTeams();
    }

    public void addTeam(AddTeamDTO dto) {
        rest.postForObject(base + "/teams", dto, AddTeamDTO.class);
    }

    public void deleteTeam(Integer id) {
        rest.delete(base + "/teams/{id}", id);
    }

    public void updateTeamName(UpdateTeamDTO updateTeamDTO) {
        rest.put(base + "/teams", updateTeamDTO, UpdateTeamDTO.class);
    }

//    RACE

    public List<Race> getRaces() {
        final var result = rest.getForObject(base + "/races", GetRacesDTO.class);
        return result.getRaces();
    }

    public void addRace(AddRaceDTO dto) {
        rest.postForObject(base + "/races", dto, AddRaceDTO.class);
    }

    public void deleteRace(Integer id) {
        rest.delete(base + "/races/{id}", id);
    }

    public void updateRaceName(UpdateRaceDTO updateRaceDTO) {
        rest.put(base + "/races", updateRaceDTO, UpdateRaceDTO.class);
    }

//    PARTICIPATION

    public List<Participation> getParticipations() {
        final var result = rest.getForObject(base + "/participations", GetParticipationsDTO.class);
        return result.getParticipations();
    }

    public List<Race> getRacesForTeam(Integer teamId) {
//        String url = base + "/participations/races/" + teamId;
        final var result = rest.getForObject(base + "/participations/races/" + teamId,
                GetRacesDTO.class);
        return result.getRaces();
    }

    public List<Team> getTeamsForRaces(Integer raceId) {
//        String url = base + "/participations/teams/" + raceId;
        final var result = rest.getForObject(base + "/participations/teams/" + raceId,
                GetTeamsDTO.class);
        return result.getTeams();
    }

    public void addParticipation(AddParticipationDTO dto) {
        rest.postForObject(base + "/participations", dto, AddParticipationDTO.class);
    }

    public void deleteParticipation(Integer id) {
        rest.delete(base + "/participations/{id}", id);
    }

}
