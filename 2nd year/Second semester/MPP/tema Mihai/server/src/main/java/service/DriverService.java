package service;

import domain.Driver;
import org.springframework.jdbc.core.JdbcOperations;
import repository.DriverRepository;
import repository.Repository;
import java.util.List;
import java.util.Optional;

public class DriverService implements Service<Driver> {

    Repository<Driver> repository;

    public DriverService() {
        repository = new DriverRepository();
    }

    public DriverService(JdbcOperations jdbc) {
        repository = new DriverRepository(jdbc);
    }

    public DriverService(Repository<Driver> driverRepository) {
        repository = driverRepository;
    }

    @Override
    public List<Driver> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Driver> getById(int id) {
        return repository.findOne(id);
    }

    @Override
    public void add(Driver entity) {
        repository.save(entity);
    }

    @Override
    public void update(Driver entity) {
        repository.update(entity);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Repository<Driver> getRepository() {
        return repository;
    }
}
