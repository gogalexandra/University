package org.example.ui;

import dto.driver.AddDriverDTO;
import dto.driver.UpdateDriverDTO;
import dto.participation.AddParticipationDTO;
import dto.race.AddRaceDTO;
import dto.race.UpdateRaceDTO;
import dto.team.AddDriverToTeamDTO;
import dto.team.AddTeamDTO;
import dto.team.UpdateTeamDTO;
import entities.Driver;
import entities.Team;
import org.example.service.Service;

import java.util.List;
import java.util.Scanner;

public class Console {
    private final Service service;
    private final Scanner scanner = new Scanner(System.in);

    public Console(Service service) {
        this.service = service;
    }

    private void printMenu() {
        System.out.println("Insert 0 to exit.");
        System.out.println("Insert 1 to add a driver.");
        System.out.println("Insert 2 to get drivers.");
        System.out.println("Insert 3 to delete a driver.");
        System.out.println("Insert 4 to update driver.");

        System.out.println("Insert 5 to add a team.");
        System.out.println("Insert 6 to get teams.");
        System.out.println("Insert 7 to delete a team.");
        System.out.println("Insert 8 to update team's name.");
        System.out.println("Insert 9 to add driver to a team.");
        System.out.println("Insert 10 to delete driver from a team.");

        System.out.println("Insert 11 to add a race.");
        System.out.println("Insert 12 to get races.");
        System.out.println("Insert 13 to delete a race.");
        System.out.println("Insert 14 to update race name.");

        System.out.println("Insert 15 to add a participation.");
        System.out.println("Insert 16 to get participations.");
        System.out.println("Insert 17 to delete a participation.");

        System.out.println("Insert 18 to get drivers in alphabetical order.");

//        System.out.println("""
//
//
//            15. Add participation
//            16. Get participations
//            17. Remove participation
//
//            18. Get participations with points greater than
//            19. Sort drivers alphabetically
//            20. Get teams with member count
//
//            0. Exit
//            """);
    }

    public void mainLoop() {
        while (true) {
            printMenu();
            final var choice = scanner.nextInt();
            if (choice == 0) {
                return;
            }
            switch (choice) {
                case 1:
                    addDriver();
                    break;
                case 2:
                    getDrivers();
                    break;
                case 3:
                    deleteDriver();
                    break;
                case 4:
                    updateDriverName();
                    break;
                case 5:
                    addTeam();
                    break;
                case 6:
                    getTeams();
                    break;
                case 7:
                    deleteTeam();
                    break;
                case 8:
                    updateTeamName();
                    break;
                case 9:
                    addDriverToTeam();
                    break;
                case 10:
                    removeDriverFromTeam();
                    break;
                case 11:
                    addRace();
                    break;
                case 12:
                    getRaces();
                    break;
                case 13:
                    deleteRace();
                    break;
                case 14:
                    updateRaceName();
                    break;
                case 15:
                    addParticipation();
                    break;
                case 16:
                    getParticipations();
                    break;
                case 17:
                    deleteParticipation();
                    break;
                case 18:
                    getDriversAlpahabetically();
//
//                case 18 -> getParticipationsWithPointsGreaterThan();
//                case 19 -> getDriversAlphabetically();
//                case 20 -> getTeamsMemberCount();
                default:
                {}
//                case 1 -> addDriver();
//                case 2 -> getDrivers();
//                case 3 -> deleteDriver();
//                case 4 -> updateDriverName();
//
//                case 5 -> addTeam();
//                case 6 -> getTeams();
//                case 7 -> deleteTeam();
//                case 8 -> updateTeamName();
//
//                case 9 -> addDriverToTeam();
//                case 10 -> removeDriverFromTeam();
//
//                case 11 -> addRace();
//                case 12 -> getRaces();
//                case 13 -> deleteRace();
//                case 14 -> updateRaceName();
//
//                case 15 -> addParticipation();
//                case 16 -> getParticipations();
//                case 17 -> deleteParticipation();
//
//                case 18 -> getRacesForTeam();
//                case 19 -> getTeamsForRace();
//                case 20 -> getDriversAlpahabetically();
//                default -> {}
            }
        }
    }

    private void getDriversAlpahabetically() {
        service.getDriversAlphabetically().forEach(System.out::println);
    }

    private void getDrivers() {
        service.getDrivers().forEach(System.out::println);
    }

    private void addDriver() {
        System.out.print("name> ");
        final var name = scanner.next();
        System.out.print("experience> ");
        final var experience = scanner.nextInt();
        System.out.print("team> ");
        final var team = scanner.nextInt();
        final var driver = new AddDriverDTO(name, experience, team);
        service.addDriver(driver);
    }

    private void deleteDriver() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        service.deleteDriver(id);
    }

    private void updateDriverName() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        System.out.print("name> ");
        final var name = scanner.next();
        System.out.print("experience> ");
        final var experience = scanner.nextInt();
        System.out.print("team> ");
        final var team = scanner.nextInt();
        service.updateDriver(new Driver(id, name, experience, team));
    }

    private void getTeams() {
        List<Team> teams = service.getTeams();
        System.out.println("Teams:\n");
        teams.stream().forEach(team -> {
            System.out.println(team);
            service.getDriversForTeam(team.getId()).forEach(System.out::println);
        });

    }

    private void addTeam() {
        System.out.print("name> ");
        final var name = scanner.next();
        final var team = new AddTeamDTO(name);
        service.addTeam(team);
    }

    private void deleteTeam() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        service.deleteTeam(id);
    }

    private void updateTeamName() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        System.out.print("name> ");
        final var name = scanner.next();
        service.updateTeamName(new UpdateTeamDTO(id, name));
    }

    private void getRaces() {
        System.out.println(service.getRaces());
    }

    private void addRace() {
        System.out.print("location> ");
        final var location = scanner.next();
        System.out.print("date> ");
        final var date = scanner.next();
        final var race = new AddRaceDTO(location, date);
        service.addRace(race);
    }

    private void deleteRace() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        service.deleteRace(id);
    }

    private void updateRaceName() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        System.out.print("location> ");
        final var location = scanner.next();
        System.out.print("date> ");
        final var date = scanner.next();
        service.updateRaceName(new UpdateRaceDTO(id, location, date));
    }

    private void getParticipations() {
        System.out.println(service.getParticipations());
    }

    private void addParticipation() {
        System.out.print("team id> ");
        final var teamId = scanner.nextInt();
        System.out.print("race id> ");
        final var raceId = scanner.nextInt();
        final var participation = new AddParticipationDTO(teamId, raceId);
        service.addParticipation(participation);
    }

    private void deleteParticipation() {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        service.deleteParticipation(id);
    }

    private void addDriverToTeam() {
        System.out.print("team id> ");
        final var teamId = scanner.nextInt();
        System.out.print("driver id> ");
        final var driverId = scanner.nextInt();
        service.addDriverToTeam(teamId, driverId);
    }

    private void removeDriverFromTeam() {
        System.out.print("team id> ");
        final var teamId = scanner.nextInt();
        System.out.print("driver id> ");
        final var driverId = scanner.nextInt();
        service.removeDriverFromTeam(teamId, driverId);
    }

//    private void getRacesForTeam() {
//        System.out.print("team id> ");
//        final var teamId = scanner.nextInt();
//        System.out.println(service.getRacesForTeam(teamId));
//    }
//
//    private void getTeamsForRace() {
//        System.out.print("race id> ");
//        final var raceId = scanner.nextInt();
//        System.out.println(service.getTeamsForRaces(raceId));
//    }
}
