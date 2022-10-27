package service;


import domain.Participation;
import domain.Race;
import domain.Team;
import org.springframework.jdbc.core.JdbcOperations;
import repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class ParticipationService implements Service<Participation> {

    Repository<Participation> repository;
    Repository<Race> raceRepository;
    Repository<Team> teamRepository;

    public ParticipationService(JdbcOperations jdbc, Repository<Race> raceRepository, Repository<Team> teamRepository) {
        this.raceRepository = raceRepository;
        this.teamRepository = teamRepository;
    }

    public ParticipationService(Repository<Race> raceRepository, Repository<Team> teamRepository) {
        this.raceRepository = raceRepository;
        this.teamRepository = teamRepository;
    }

    public Iterable<Participation> getParticipationsForRace(Race race) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(participation -> participation.getRace() == race.getId())
                ::iterator;
    }

    @Override
    public List<Participation> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Participation> getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public void add(Participation entity) {
        if(raceRepository.findOne(entity.getRace()).isPresent()
                && teamRepository.findOne(entity.getTeam()).isPresent()) {
            repository.save(entity);
        }
        else {
            throw new RuntimeException("Team or Race don't exist");
        }
    }

    @Override
    public void update(Participation entity) {
        if(raceRepository.findOne(entity.getRace()).isPresent()
                && teamRepository.findOne(entity.getTeam()).isPresent()) {
            repository.update(entity);
        }
        else {
            throw new RuntimeException("Team or Race don't exist");
        }
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Repository<Participation> getRepository() {
        return repository;
    }
}

