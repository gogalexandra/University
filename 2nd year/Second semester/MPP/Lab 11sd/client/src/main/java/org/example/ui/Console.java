package org.example.ui;

import dto.driver.AddDriverDTO;
import dto.driver.UpdateDriverDTO;
import dto.participation.AddParticipationDTO;
import dto.race.AddRaceDTO;
import dto.race.UpdateRaceDTO;
import dto.team.AddDriverToTeamDTO;
import dto.team.AddTeamDTO;
import dto.team.UpdateTeamDTO;
import org.example.service.Service;

import javax.xml.transform.Source;
import java.awt.*;
import java.sql.Driver;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final Service service;
    private final Scanner scanner = new Scanner(System.in);

    public Console(Service service) {
        this.service = service;
    }

    private void printMenu() {
        System.out.println("""
            1. Add driver
            2. Get drivers
            3. Delete driver
            4. Update driver name
            
            5. Add team
            6. Get teams
            7. Delete team
            8. Update team name
            9. Add driver to team
            10. Remove driver's team
            
            11. Add race
            12. Get races
            13. Delete race
            14. Update race name
            
            15. Add participation
            16. Get participations
            17. Remove participation
            
            18. Get participations with points greater than
            19. Sort drivers alphabetically
            20. Get teams with member count            
            
            0. Exit
            """);
    }

    public void mainLoop() {
        while (true) {
            printMenu();
            final var choice = scanner.nextInt();
            if (choice == 0) {
                return;
            }
            switch (choice) {
                case 1 -> addDriver();
                case 2 -> getDrivers();
                case 3 -> deleteDriver();
                case 4 -> updateDriverName();

                case 5 -> addTeam();
                case 6 -> getTeams();
                case 7 -> deleteTeam();
                case 8 -> updateTeamName();

                case 9 -> addDriverToTeam();
                case 10 -> removeDriverFromTeam();

                case 11 -> addRace();
                case 12 -> getRaces();
                case 13 -> deleteRace();
                case 14 -> updateRaceName();

                case 15 -> addParticipation();
                case 16 -> getParticipations();
                case 17 -> deleteParticipation();

                case 18 -> getParticipationsWithPointsGreaterThan();
                case 19 -> getDriversAlphabetically();
                case 20 -> getTeamsMemberCount();
                default -> {}
            }
        }
    }

    private void getDrivers() {
        System.out.println(service.getDrivers());
    }

    private void addDriver() {
        System.out.print("name> ");
        final var name = scanner.next();
        final var driver = new AddDriverDTO(name);
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
        service.updateDriverName(id, new UpdateDriverDTO(name));
    }

    private void getTeams() {
        System.out.println(service.getTeams());
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
        service.updateTeamName(id, new UpdateTeamDTO(name));
    }

    private void getRaces() {
        System.out.println(service.getRaces());
    }

    private void addRace() {
        System.out.print("name> ");
        final var name = scanner.next();
        final var race = new AddRaceDTO(name);
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
        System.out.print("name> ");
        final var name = scanner.next();
        service.updateRaceName(id, new UpdateRaceDTO(name));
    }

    private void getParticipations() {
        System.out.println(service.getParticipations());
    }

    private void addParticipation() {
        System.out.print("team id> ");
        final var teamId = scanner.nextInt();
        System.out.print("race id> ");
        final var raceId = scanner.nextInt();
        System.out.print("points> ");
        final var points = scanner.nextInt();
        final var participation = new AddParticipationDTO(teamId, raceId, points);
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
        service.addDriverToTeam(teamId, new AddDriverToTeamDTO(driverId));
    }

    private void removeDriverFromTeam() {
        System.out.print("driver id> ");
        final var driverId = scanner.nextInt();
        service.removeDriverFromTeam(driverId);
    }

    private void getParticipationsWithPointsGreaterThan() {
        System.out.print("points> ");
        final var points = scanner.nextInt();
        System.out.println(service.getParticipationsWithPointsGreaterThan(points));
    }

    private void getDriversAlphabetically() {
        System.out.println(service.getDriversAlphabetically());
    }

    private void getTeamsMemberCount() {
        System.out.println(service.getTeamsWithMemberCount());
    }
}
