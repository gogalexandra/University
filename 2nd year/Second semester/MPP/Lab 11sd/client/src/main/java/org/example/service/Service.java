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
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;
public class Service {

    static String base = "http://localhost:8080/api";
    final RestTemplate rest = new RestTemplate();
    public List<Driver> getDrivers() {
        final var result = rest.getForObject(base + "/drivers", GetDriversDTO.class);
        return result.getDrivers();
    }

    public void addDriver(AddDriverDTO dto) {
        rest.postForObject(base + "/drivers", dto, AddDriverDTO.class);
    }

    public void deleteDriver(Integer id) {
        rest.delete(base + "/drivers/{id}", id);
    }

    public void updateDriverName(Integer id, UpdateDriverDTO updateDriverDTO) {
        rest.put(base + "/drivers/{id}", updateDriverDTO, id);
    }

    public List<Team> getTeams() {
        final var result = rest.getForObject(base + "/teams", GetTeamsDTO.class);
        return result.getTeams();
    }

    public List<GetTeamsMemberCountDTO.InnerTeam> getTeamsWithMemberCount() {
        final var result = rest.getForObject(base + "/teams/memberCount", GetTeamsMemberCountDTO.class);
        return result.getTeams();
    }

    public void addTeam(AddTeamDTO dto) {
        rest.postForObject(base + "/teams", dto, AddTeamDTO.class);
    }

    public void deleteTeam(Integer id) {
        rest.delete(base + "/teams/{id}", id);
    }

    public void updateTeamName(Integer id, UpdateTeamDTO updateTeamDTO) {
        rest.put(base + "/teams/{id}", updateTeamDTO, id);
    }

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

    public void updateRaceName(Integer id, UpdateRaceDTO updateRaceDTO) {
        rest.put(base + "/races/{id}", updateRaceDTO, id);
    }

    public List<Participation> getParticipations() {
        final var result = rest.getForObject(base + "/participations", GetParticipationsDTO.class);
        return result.getParticipations();
    }

    public List<Participation> getParticipationsWithPointsGreaterThan(Integer points) {
        final var result = rest.getForObject(base + "/participations?pointsGreaterThan=" + points, GetParticipationsDTO.class);
        return result.getParticipations();
    }

    public void addParticipation(AddParticipationDTO dto) {
        rest.postForObject(base + "/participations", dto, AddParticipationDTO.class);
    }

    public void deleteParticipation(Integer id) {
        rest.delete(base + "/participations/{id}", id);
    }

    public void addDriverToTeam(Integer teamId, AddDriverToTeamDTO dto) {
        rest.postForObject(base + "/teams/{id}/drivers", dto, AddDriverToTeamDTO.class, teamId);
    }

    public void removeDriverFromTeam(Integer driverId) {
        rest.postForObject(base + "/drivers/{id}/removeFromTeam", new HttpEntity<>(null), HttpEntity.class, driverId);
    }

    public List<Driver> getDriversAlphabetically() {
        final var result = rest.getForObject(base + "/drivers?alphabetically=true", GetDriversDTO.class);
        return result.getDrivers();
    }
}
