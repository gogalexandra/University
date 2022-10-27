package org.example.controller;

import dto.driver.AddDriverDTO;
import dto.driver.GetDriversDTO;
import dto.driver.UpdateDriverDTO;
import dto.team.AddDriverToTeamDTO;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

@RestController
public class DriverController {
    private final DriverService driverService;
    private static Logger logger = LoggerFactory.getLogger(DriverController.class);

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/drivers")
    GetDriversDTO all(@RequestParam(required = false) boolean alphabetically) {
        logger.info("entering /drivers with GET");
        if (alphabetically) {
            return new GetDriversDTO(driverService.getDriversAlphabetically());
        }
        logger.info("exiting /drivers with GET");
        return new GetDriversDTO(driverService.getDrivers());
    }

    @PostMapping("/drivers")
    void add(@RequestBody AddDriverDTO dto) {
        logger.info("entering /drivers with POST");
        driverService.addDriver(dto);
        logger.info("entering /drivers with POST");
    }

    @DeleteMapping("/drivers/{id}")
    void delete(@PathVariable Integer id) {
        logger.info("entering /drivers/{id} with DELETE");
        driverService.deleteDriver(id);
        logger.info("exiting /drivers/{id} with DELETE");
    }

    @PutMapping("/drivers/{id}")
    void updateName(@PathVariable Integer id, @RequestBody UpdateDriverDTO dto) {
        logger.info("entering /drivers/{id} with PUT");
        driverService.updateDriver(id, dto);
        logger.info("exiting /drivers/{id} with PUT");
    }

    @PostMapping("/drivers/{id}/removeFromTeam")
    void removeFromTeam(@PathVariable Integer id) {
        driverService.removeFromTeam(id);
    }
}
