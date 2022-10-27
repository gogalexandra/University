package service;

import dto.participation.AddParticipationDTO;
import entities.Participation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ParticipationRepository;
import repository.RaceRepository;
import repository.TeamRepository;

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
        final var team = teamRepository.findById(dto.getTeamId()).get();
        final var race = raceRepository.findById(dto.getRaceId()).get();
        final var participation = new Participation(team, race, dto.getPoints());
        participationRepository.save(participation);
        team.getParticipations().add(participation);
        teamRepository.save(team);
        race.getParticipations().add(participation);
        raceRepository.save(race);
    }

    public List<Participation> getParticipations() {
        return participationRepository.findAll();
    }

    public List<Participation> getParticipationsWithPointsGreaterThan(Integer points) {
        return participationRepository.findByPointsGreaterThan(points);
    }

    @Transactional
    public void delete(Integer id) {
        final var participation = participationRepository.findById(id).get();
        final var team = teamRepository.findById(participation.getTeam().getId()).get();
        team.setParticipations(
            team.getParticipations()
                .stream()
                .filter(p -> !Objects.equals(p.getId(), id))
                .collect(Collectors.toSet())
        );
        teamRepository.save(team);
        final var race = raceRepository.findById(participation.getRace().getId()).get();
        race.setParticipations(
            race.getParticipations()
                .stream()
                .filter(p -> !Objects.equals(p.getId(), id))
                .collect(Collectors.toSet())
        );
        raceRepository.save(race);
        participationRepository.deleteById(id);
    }
}
