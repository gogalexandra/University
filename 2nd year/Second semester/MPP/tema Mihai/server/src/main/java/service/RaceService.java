package service;


import domain.Race;
import org.springframework.jdbc.core.JdbcOperations;
import repository.RaceRepository;
import repository.Repository;

import java.util.List;
import java.util.Optional;

public class RaceService implements Service<Race> {

    Repository<Race> repository;

    public RaceService() {
        this.repository = new RaceRepository();
    }

    public RaceService(JdbcOperations jdbc) {
        this.repository = new RaceRepository(jdbc);
    }

    @Override
    public List<Race> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Race> getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public void add(Race entity) {
        repository.save(entity);
    }

    @Override
    public void update(Race entity) {
        repository.update(entity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Repository<Race> getRepository() {
        return repository;
    }
}
