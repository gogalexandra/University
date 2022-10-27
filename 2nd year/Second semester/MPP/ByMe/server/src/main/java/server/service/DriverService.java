package server.service;

import common.domain.Driver;
import server.repository.DBpersistence.Repository;

public class DriverService {
    private Repository<Integer, Driver> driverRepository;

    public DriverService(Repository<Integer, Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Iterable<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public void addDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        Driver driver = new Driver(id, name, experience, carid, teamid);
        this.driverRepository.save(driver);
    }

    public void deleteDriver(Integer id) {
        this.driverRepository.delete(id);
    }

    public void updateDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        Driver driver = new Driver(id, name, experience, carid, teamid);
        this.driverRepository.update(driver);
    }
}
