package service;

import dto.driver.AddDriverDTO;
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

    public List<Driver> getDriversAlphabetically() {
        return driverRepository.findByOrderByNameAsc();
    }

    public void addDriver(AddDriverDTO dto) {
        driverRepository.save(new Driver(dto.getName()));
    }

    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    @Transactional
    public void updateDriver(Integer id, UpdateDriverDTO dto) {
        final var driver = driverRepository.findById(id).get();
        if (dto.getName() != null) {
            driver.setName(dto.getName());
        }
    }

    @Transactional
    public void removeFromTeam(Integer id) {
        final var driver = driverRepository.findById(id).get();
        driver.setTeam(null);
    }
}
