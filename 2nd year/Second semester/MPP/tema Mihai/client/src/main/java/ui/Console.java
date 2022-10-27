package ui;


import domain.Driver;
import domain.Participation;
import domain.Race;
import domain.Team;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import service.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

@Component
public class Console {
    @Autowired
    private final RaceService raceService;
    @Autowired
    private final DriverService driverService;
    @Autowired
    private final TeamService teamService;
    @Autowired
    private final ParticipationService participationService;

    final Scanner scanner = new Scanner(System.in);

    public Console() {
//        raceService = new RaceService();
//        driverService = new DriverService();
//        teamService = new TeamService();
//        participationService = new ParticipationService();
        }

//    public Console(Service<Race> raceService, Service<Driver> driverService, Service<Team> teamService,
//                   Service<Participation> participationService) {
//        this.raceService = raceService;
//        this.driverService = driverService;
//        this.teamService = teamService;
//        this.participationService = participationService;
//    }

    private JdbcOperations setDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.postgresql.Driver.class.getName());
//        dataSource.setUsername(System.getProperty("postgres"));
//        dataSource.setPassword(System.getProperty("admin"));
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/MPP_Labs");
        dataSource.setInitialSize(2);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    private void printMainMenu() {
        System.out.println("""
                2. Driver menu
                3. Race menu
                6. Team menu
                0. Exit
                """);
    }

    public void mainLoop() {
        while (true) {
            printMainMenu();
            final var option = scanner.nextInt();
            try {
                switch (option) {
                    case 0 -> System.exit(0);
//                    case 1 -> carMenu();
                    case 2 -> driverMenu();
                    case 3 -> raceMenu();
//                    case 4 -> sponsorMenu();
//                    case 5 -> teamPrincipalMenu();
                    case 6 -> teamMenu();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    // region car
    private void printCarMenu() {
        System.out.println("""
                1. Add car
                2. Print cars
                3. Update name
                4. Update horsepower
                5. Update max speed
                6. Delete car
                0. Exit
                """);
    }


    // region driver
    private void printDriverMenu() {
        System.out.println("""
                1. Add driver
                2. Print drivers
                3. Update name
                4. Update experience
                5. Update car
                6. Remove driver
                0. Exit
                """);
    }

    private void driverMenu() throws Exception {
        loop:
        while (true) {
            printDriverMenu();
            final var option = scanner.nextInt();
            switch (option) {
                case 1 -> addDriver();
                case 2 -> printDrivers();
                case 3 -> updateDriverName();
                case 4 -> updateDriverExperience();
                case 5 -> updateDriverCar();
                case 6 -> deleteDriver();
                case 0 -> {
                    break loop;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addDriver() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Name> ");
        final var name = scanner.next();
        System.out.print("Experience> ");
        final var experience = scanner.nextInt();
//        System.out.print("ID of his car> ");
//        final var carID = scanner.nextInt();
        driverService.add(id, name, experience);
    }

    private void printDrivers() throws ExecutionException, InterruptedException {
        driverService.getAll().get().forEach(driver -> System.out.println(driver.toString()));
    }

    private void updateDriverName() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Name> ");
        final var name = scanner.next();
//        driverService.updateName(id, name);
    }

    private void updateDriverExperience() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Experience> ");
        final var experience = scanner.nextInt();
//        driverService.updateExperience(id, experience);
    }

    private void updateDriverCar() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Car id> ");
        final var carId = scanner.nextInt();
//        driverService.updateCar(id, carId);
    }

    private void deleteDriver() {
        System.out.print("ID> ");
        final var id = scanner.nextInt();

        try {
            driverService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // endregion driver

    // region race
    private void printRaceMenu() {
        System.out.println("""
                1. Add race
                2. Print races
                0. Exit
                """);
    }

    private void raceMenu() throws Exception {
        loop:
        while (true) {
            printRaceMenu();
            final var option = scanner.nextInt();
            switch (option) {
                case 1 -> addRace();
                case 2 -> printRaces();
                case 3 -> updateRaceLocation();
                case 4 -> updateRaceDate();
                case 0 -> {
                    break loop;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void addRace() throws ParseException {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Location> ");
        final var location = scanner.next();
        System.out.print("Date> ");
        final var dateString = scanner.next();
        final var formatter = new SimpleDateFormat("yyyy-MM-dd");
        final var date = formatter.parse(dateString);
        raceService.add(id, location, date);
    }

    private void printRaces() {
//        raceService.getAll().forEach(race -> {
//            System.out.println(race.toString());
//            System.out.println("Teams: ");
//            participationService
//                    .getParticipationsForRace(race)
//                    .forEach(participation -> System.out.println(teamService.getById(participation.getTeam())));
//        });
    }

    private void updateRaceLocation() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Location> ");
        final var location = scanner.next();
//        raceService.updateLocation(id, location);
    }

    private void updateRaceDate() throws Exception {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Date> ");
        final var date = scanner.next();
//        raceService.updateDate(id, date);
    }
    // endregion race


    // region team

    private void printTeamMenu() {
        System.out.println("""
                1. Add team
                2. Print team
                3. Update name
                4. Add driver
                5. Remove driver
                6. Add sponsor
                7. Remove sponsor
                8. Edit team principal
                9. Add to race
                10. Delete team
                0. Exit
                """);
    }

    private void teamMenu() throws Exception {
        loop:
        while (true) {
            printTeamMenu();
            final var option = scanner.nextInt();
            switch (option) {
                case 1 -> addTeam();
                case 2 -> printTeams();
                case 3 -> updateTeamName();
                case 4 -> updateTeamDriverAdd();
                case 5 -> updateTeamDriverRemove();
                case 6 -> updateTeamSponsorAdd();
                case 7 -> updateTeamSponsorRemove();
                case 8 -> updateTeamTeamPrincipal();
                case 9 -> addTeamToRace();
                case 10 -> deleteTeam();
                case 0 -> {
                    break loop;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void deleteTeam() {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        try {
            teamService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addTeam() {
        System.out.print("ID> ");
        final var id = scanner.nextInt();
        System.out.print("Name> ");
        final var name = scanner.next();
        System.out.print("TeamPrincipal id> ");
        final var teamPrincipalId = scanner.nextInt();
        try {
//            teamService.add(id, name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void printTeams() throws ExecutionException, InterruptedException {
        teamService.getAll().get().forEach(team -> System.out.println(team.toString()));
    }

    private void updateTeamName() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Name> ");
        var name = scanner.next();
//        teamService.updateTeamName(id, name);
    }

    private void updateTeamDriverAdd() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Driver Id> ");
        var driverId = scanner.nextInt();
//        teamService.updateTeamDriverAdd(id, driverId);
    }

    private void updateTeamDriverRemove() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Driver Id> ");
        var driverId = scanner.nextInt();
//        teamService.updateTeamDriverRemove(id, driverId);
    }

    private void updateTeamSponsorAdd() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Sponsor Id> ");
        var sponsorId = scanner.nextInt();
//        teamService.updateTeamSponsorAdd(id, sponsorId);
    }

    private void updateTeamSponsorRemove() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Sponsor Id> ");
        var sponsorId = scanner.nextInt();
//        teamService.updateTeamSponsorRemove(id, sponsorId);
    }

    private void updateTeamTeamPrincipal() throws Exception {
        System.out.print("Id> ");
        var id = scanner.nextInt();
        System.out.print("Team principal id> ");
        var principalId = scanner.nextInt();
//        teamService.updateTeamTeamPrincipal(id, principalId);
    }

    private void addTeamToRace() throws Exception {
        System.out.print("id> ");
        final var id = scanner.nextInt();
        System.out.print("team id> ");
        final var teamId = scanner.nextInt();
        System.out.print("race id> ");
        final var raceId = scanner.nextInt();
//        participationService.addParticipation(id, teamId, raceId);
    }
    // endregion team
}
