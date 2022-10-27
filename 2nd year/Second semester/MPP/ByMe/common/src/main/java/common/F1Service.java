package common;

import common.domain.*;

import java.util.concurrent.CompletableFuture;

public interface F1Service {
    int PORT = 1234;
    String HOST = "localhost";
    String GET_DRIVERS = "getDrivers";
    String GET_CAR = "getCar";
    String GET_PARTICIPATION = "getParticipation";
    String GET_RACE = "getRace";
    String GET_TEAM = "getTeam";
    String ADD_DRIVER = "addDrivers";
    String ADD_CAR = "addCar";
    String ADD_PARTICIPATION = "addParticipation";
    String ADD_RACE = "addRace";
    String ADD_TEAM = "addTeam";
    String DELETE_DRIVER = "deleteDrivers";
    String DELETE_CAR = "deleteCar";
    String DELETE_PARTICIPATION = "deleteParticipation";
    String DELETE_RACE = "deleteRace";
    String DELETE_TEAM = "deleteTeam";
    String UPDATE_DRIVER = "updateDrivers";
    String UPDATE_CAR = "updateCar";
    String UPDATE_PARTICIPATION = "updateParticipation";
    String UPDATE_RACE = "updateRace";
    String UPDATE_TEAM = "updateTeam";
    String GET_RACE_PARTICIPATION = "getRaceParticipation";
    String GET_TEAM_PARTICIPATION = "getTeamParticipation";
    String GET_CAR_DRIVER = "getCarDriver";
    String GET_TEAM_DRIVERS = "getTeamDrivers";

    //Driver
    CompletableFuture<Iterable<Driver>> getDrivers();
    CompletableFuture<String> addDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid);
    CompletableFuture<String> deleteDriver(Integer id);
    CompletableFuture<String> updateDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid);
    //Car
    CompletableFuture<Iterable<Car>> getCars();
    CompletableFuture<String> addCar(Integer id, String name, Integer horsepower, Integer maxspeed);
    CompletableFuture<String> deleteCar(Integer id);
    CompletableFuture<String> updateCar(Integer id, String name, Integer horsepower, Integer maxSpeed);
    CompletableFuture<Iterable<Driver>> getCarDriver(Integer id);
    //Team
    CompletableFuture<Iterable<Team>> getTeams();
    CompletableFuture<String> addTeam(Integer id, String name, Integer teamPrincipalId);
    CompletableFuture<String> deleteTeam(Integer id);
    CompletableFuture<String> updateTeam(Integer id, String name, Integer teamPrincipalId);
    CompletableFuture<Iterable<Participation>> getTeamParticipation(Integer id);
    CompletableFuture<Iterable<Driver>> getTeamDrivers(Integer id);
    //Race
    CompletableFuture<Iterable<Race>> getRaces();
    CompletableFuture<String> addRace(Integer id, String location, String date);
    CompletableFuture<String> deleteRace(Integer id);
    CompletableFuture<String> updateRace(Integer id, String location, String date);
    CompletableFuture<Iterable<Participation>> getRaceParticipation(Integer id);
    //Participation
    CompletableFuture<Iterable<Participation>> getParticipations();
    CompletableFuture<String> addParticipation(Integer id, Integer teamid, Integer raceid);
    CompletableFuture<String> deleteParticipation(Integer id);
    CompletableFuture<String> updateParticipation(Integer id, Integer teamid, Integer raceid);
}

