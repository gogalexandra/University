package service;

import dto.driver.AddDriverDTO;
import dto.driver.DriverDTO;
import dto.driver.UpdateDriverDTO;
import dto.team.AddDriverToTeamDTO;
import entities.Driver;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DriverRepository;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public  List<Driver> getDriversForTeam(Integer teamId) {
        return driverRepository.findAllByTeam(teamId);
    }

    public void addDriver(AddDriverDTO dto) {
        driverRepository.save(new Driver(dto));
    }

    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    @Transactional
    public void updateDriver(AddDriverDTO driver) {
        driverRepository.save(new Driver(driver));
    }

    public List<Driver> getDriversAlphabetically() {
        return driverRepository.findByOrderByNameAsc();
    }
}
