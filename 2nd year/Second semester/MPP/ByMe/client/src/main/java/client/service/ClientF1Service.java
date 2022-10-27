package client.service;

import client.tcp.TcpClient;
import common.EntityConverter;
import common.F1Service;
import common.domain.*;
import main.java.common.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class ClientF1Service implements F1Service {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public ClientF1Service(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Iterable<Driver>> getDrivers() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_DRIVERS, "");
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String result = response.getBody();
            List<Driver> drivers = new ArrayList<>();
            Arrays.stream(result.split("!")).map(EntityConverter::destringifyDriver)
                    .forEach(drivers::add);
            return drivers;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + experience + "," + carid + "," + teamid;
            Message request = new Message(F1Service.ADD_DRIVER, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteDriver(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_DRIVER, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateDriver(Integer id, String name, Integer experience, Integer carid, Integer teamid) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + experience + "," + carid + "," + teamid;
            Message request = new Message(F1Service.UPDATE_DRIVER, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Car>> getCars() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_CAR, "");
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Car> cars = new ArrayList<>();
            Arrays.stream(result.split("!")).map(EntityConverter::destringifyCar)
                    .forEach(cars::add);
            return cars;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addCar(Integer id, String name, Integer horsepower, Integer maxspeed) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + horsepower + "," + maxspeed;
            Message request = new Message(F1Service.ADD_CAR, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteCar(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_CAR, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateCar(Integer id, String name, Integer horsepower, Integer maxspeed) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + horsepower + "," + maxspeed;
            Message request = new Message(F1Service.UPDATE_CAR, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Driver>> getCarDriver(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_CAR_DRIVER, "" + id);
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Driver> drivers = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyDriver)
                    .forEach(drivers::add);
            return drivers;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Participation>> getParticipations() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_PARTICIPATION, "");
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Participation> participations = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyParticipation)
                    .forEach(participations::add);
            return participations;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addParticipation(Integer id, Integer teamid, Integer raceid) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + teamid + "," + raceid;
            Message request = new Message(F1Service.ADD_PARTICIPATION, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteParticipation(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_PARTICIPATION, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateParticipation(Integer id, Integer teamid, Integer raceid) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + teamid + "," + raceid;
            Message request = new Message(F1Service.UPDATE_PARTICIPATION, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Team>> getTeams() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_TEAM, "");
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Team> teams = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyTeam)
                    .forEach(teams::add);
            return teams;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> addTeam(Integer id, String name, Integer teamPrincipalId) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + teamPrincipalId;
            Message request = new Message(F1Service.ADD_TEAM, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteTeam(Integer id) {
        CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_PARTICIPATION, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_TEAM, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateTeam(Integer id, String name, Integer teamPrincipalId) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + name + "," + teamPrincipalId;
            Message request = new Message(F1Service.UPDATE_TEAM, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Participation>> getTeamParticipation(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_TEAM_PARTICIPATION, "" + id);
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Participation> participations = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyParticipation)
                    .forEach(participations::add);
            return participations;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Driver>> getTeamDrivers(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_TEAM_DRIVERS, "" + id);
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Driver> drivers = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyDriver)
                    .forEach(drivers::add);
            return drivers;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Race>> getRaces() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_RACE, "");
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Race> races = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyRace)
                    .forEach(races::add);
            return races;
        }, executorService);

    }

    @Override
    public CompletableFuture<String> addRace(Integer id, String location, String date) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + location + "," + date;
            Message request = new Message(F1Service.ADD_RACE, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> deleteRace(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.DELETE_RACE, "" + id);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<String> updateRace(Integer id, String location, String date) {
        return CompletableFuture.supplyAsync(() -> {
            String body = id + "," + location + "," + date;
            Message request = new Message(F1Service.UPDATE_RACE, body);
            try {
                return tcpClient.sendAndReceive(request).getBody();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Iterable<Participation>> getRaceParticipation(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(F1Service.GET_RACE_PARTICIPATION, "" + id);
            Message response = null;
            try {
                response = tcpClient.sendAndReceive(request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String result = response.getBody();
            List<Participation> participations = new ArrayList<>();
            Arrays.stream(result.split("!"))
                    .map(EntityConverter::destringifyParticipation)
                    .forEach(participations::add);
            return participations;
        }, executorService);
    }
}
