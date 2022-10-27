package service;

import domain.Driver;
import domain.Team;
import org.springframework.jdbc.core.JdbcOperations;
import repository.Repository;
import repository.TeamRepository;

import java.util.List;
import java.util.Optional;

public class TeamService implements Service<Team> {

    Repository<Team> repository;
    Repository<Driver> driverRepository;

    public TeamService(JdbcOperations jdbc, Repository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
        this.repository = new TeamRepository(jdbc);
    }

    public TeamService(Repository<Driver> repository) {
        this.driverRepository = repository;
    }

    @Override
    public List<Team> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Team> getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public void add(Team entity) throws RuntimeException {
        for(var id : entity.getDrivers()){
            if(driverRepository.findOne(id).isEmpty()) {
                throw new RuntimeException("Invalid driver id: " + id);
            }
        }
        repository.save(entity);
    }

    @Override
    public void update(Team entity) throws RuntimeException {
        for(var id : entity.getDrivers()){
            if(driverRepository.findOne(id).isEmpty()) {
                throw new RuntimeException("Invalid driver id: " + id);
            }
        }
        repository.update(entity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Repository<Team> getRepository() {
        return repository;
    }
}
