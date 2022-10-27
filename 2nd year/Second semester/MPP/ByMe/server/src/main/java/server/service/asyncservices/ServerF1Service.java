package server.service.asyncservices;


import common.F1Service;
import common.domain.*;
import server.service.*;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ServerF1Service implements F1Service {
    private ExecutorService executorService;
    private DriverService driverService;
    private CarService carService;
    private ParticipationService participationService;
    private TeamService teamService;
    private RaceService raceService;

    public ServerF1Service(ExecutorService executorService, DriverService employeeService, CarService clientService, ParticipationService foodService, TeamService toyService, RaceService raceService) {
        this.executorService = executorService;
        this.driverService = employeeService;
        this.carService = clientService;
        this.participationService = foodService;
        this.teamService = toyService;
        this.raceService = raceService;
    }

    @Override
    public CompletableFuture<Iterable<Driver>> getDrivers() {
        return CompletableFuture.supplyAsync(driverService::getDrivers, executorService);
    }

    @Override
    public CompletableFuture<String> addDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                driverService.addDriver(id, name, experience, carid, teamid);
                return "Driver added";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteDriver(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            try{
                driverService.deleteDriver(id);
                return "Driver deleted";
            }
            catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                driverService.updateDriver(id, name, experience, carid, teamid);
                return "Driver updateded";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);    }


    @Override
    public CompletableFuture<Iterable<Car>> getCars() {
        return CompletableFuture.supplyAsync(carService::getCars, executorService);
    }

    @Override
    public CompletableFuture<String> addCar(Integer id, String name, Integer horsepower, Integer maxSpeed) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                carService.addCar(id, name, horsepower, maxSpeed);
                return "Car added";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteCar(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            try{
                carService.deleteCar(id);
                return "Car deleted";
            }
            catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateCar(Integer id, String name, Integer horsepower, Integer maxSpeed) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                carService.updateCar(id, name, horsepower, maxSpeed);
                return "Car updated";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);    }

    @Override
    public CompletableFuture<Iterable<Driver>> getCarDriver(Integer id) {
        return CompletableFuture.supplyAsync(()->carService.getCarDriver(id), executorService);
    }


    @Override
    public CompletableFuture<Iterable<Team>> getTeams() {
        return CompletableFuture.supplyAsync(teamService::getTeams, executorService);
    }

    @Override
    public CompletableFuture<String> addTeam(Integer id, String name, Integer teamPrincipalId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                teamService.addTeam(id, name, teamPrincipalId);
                return "Team added";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteTeam(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            try{
                teamService.deleteTeam(id);
                return "Team deleted";
            }
            catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateTeam(Integer id, String name, Integer teamPrincipalId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                teamService.updateTeam(id, name, teamPrincipalId);
                return "Team updated";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);        }

    @Override
    public CompletableFuture<Iterable<Participation>> getTeamParticipation(Integer id) {
        return CompletableFuture.supplyAsync(()->teamService.getTeamParticipations(id), executorService);

    }

    @Override
    public CompletableFuture<Iterable<Driver>> getTeamDrivers(Integer id) {
        return CompletableFuture.supplyAsync(()->teamService.getTeamDrivers(id), executorService);
    }

    @Override
    public CompletableFuture<Iterable<Race>> getRaces() {
        return CompletableFuture.supplyAsync(raceService::getRaces, executorService);
    }

    @Override
    public CompletableFuture<String> addRace(Integer id, String location, String date) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                raceService.addRace(id, location, date);
                return "Race added";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteRace(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            try{
                raceService.deleteRace(id);
                return "Race deleted";
            }
            catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateRace(Integer id, String location, String date) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                raceService.updateRace(id, location, date);
                return "Race updated";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Participation>> getRaceParticipation(Integer id) {
        return CompletableFuture.supplyAsync(()->raceService.getRaceParticipations(id), executorService);
    }

    @Override
    public CompletableFuture<Iterable<Participation>> getParticipations() {
        return CompletableFuture.supplyAsync(participationService::getParticipations, executorService);

    }

    @Override
    public CompletableFuture<String> addParticipation(Integer id, Integer teamid, Integer raceid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                participationService.addParticipation(id, teamid, raceid);
                return "Participation added";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteParticipation(Integer id) {
        return CompletableFuture.supplyAsync(()->{
            try{
                participationService.deleteParticipation(id);
                return "Partcipation deleted";
            }
            catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateParticipation(Integer id, Integer teamid, Integer raceid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                participationService.updateParticipation(id, teamid, raceid);
                return "Participation updated";
            }catch (Exception exception){
                return exception.getMessage();
            }
        }, executorService);
    }
}
