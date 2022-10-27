package org.example.controller;

import dto.driver.AddDriverDTO;
import dto.driver.GetDriversDTO;
import dto.driver.UpdateDriverDTO;
import entities.Driver;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

import java.util.List;

@RestController
public class DriverController {
    private final DriverService driverService;
    private static Logger logger = LoggerFactory.getLogger(DriverController.class);

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/drivers")
    GetDriversDTO all() {
        logger.info("entering /drivers with GET");
        logger.info("exiting /drivers with GET");
        return new GetDriversDTO(driverService.getDrivers());
    }

    @GetMapping("/drivers/team/{teamId}")
    GetDriversDTO getDriversForTeam(@PathVariable Integer teamId) {
        logger.info("entering /drivers/team with GET");
        List<Driver> list = driverService.getDriversForTeam(teamId);
        logger.info("exiting /drivers/team with GET");
        return new GetDriversDTO(list);
    }

    @GetMapping("/drivers/alphabetically")
    GetDriversDTO getDriversAlphabetically() {
        logger.info("entering /drivers/team with GET");
        List<Driver> list = driverService.getDriversAlphabetically();
        logger.info("exiting /drivers/team with GET");
        return new GetDriversDTO(list);
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

    @PutMapping("/drivers")
    void update(@RequestBody AddDriverDTO dto) {
        logger.info("entering /drivers/{id} with PUT");
        driverService.updateDriver(dto);
        logger.info("exiting /drivers/{id} with PUT");
    }
}
