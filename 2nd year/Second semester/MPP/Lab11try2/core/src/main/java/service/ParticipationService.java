package service;

import dto.participation.AddParticipationDTO;
import entities.Participation;
import entities.Race;
import entities.Team;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParticipationRepository;
import repository.RaceRepository;
import repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final TeamRepository teamRepository;
    private final RaceRepository raceRepository;

    public ParticipationService(ParticipationRepository participationRepository, TeamRepository teamRepository, RaceRepository raceRepository) {
        this.participationRepository = participationRepository;
        this.teamRepository = teamRepository;
        this.raceRepository = raceRepository;
    }

    public void addParticipation(AddParticipationDTO dto) {
        participationRepository.save(new Participation(dto));
    }

    public List<Participation> getParticipations() {
        return participationRepository.findAll();
    }

    @Transactional
    public void delete(Integer id) {
        participationRepository.deleteById(id);
    }

    public List<Race> getRacesForTeam(Integer teamId){
        return participationRepository.findParticipationByTeam(teamId).stream()
                .map(participation -> raceRepository.getRaceById(participation.getRace()))
                .collect(Collectors.toList());
    }

    public List<Team> getTeamsForRace(Integer raceId) {
        return participationRepository.findParticipationByRace(raceId).stream()
                .map(participation -> teamRepository.getTeamById(participation.getTeam()))
                .collect(Collectors.toList());
    }
}
