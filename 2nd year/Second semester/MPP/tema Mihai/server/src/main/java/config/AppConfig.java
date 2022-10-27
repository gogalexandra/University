package config;

import common.Constants;
import domain.Driver;
import domain.Participation;
import domain.Race;
import domain.Team;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repository.*;
import service.*;
import tcp.ApplicationServer;
import tcp.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    ApplicationServer server() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        return new ApplicationServer(executorService, Constants.PORT);
    }

    @Bean
    Service<Driver> driverService() {
        return new DriverService(driverRepository());
    }

    @Bean
    Service<Race> raceService() {
        return new RaceService();
    }

    @Bean
    Service<Team> teamService() {
        return new TeamService(driverRepository());
    }

    @Bean
    Service<Participation> participationService() {
        return new ParticipationService(raceRepository(), teamRepository());
    }

    @Bean
    Repository<Driver> driverRepository() {
        return new DriverRepository();
    }

    @Bean
    Repository<Team> teamRepository() {
        return new TeamRepository();
    }

    @Bean
    Repository<Participation> participationRepository() {
        return new ParticipationRepository();
    }

    @Bean
    Repository<Race> raceRepository() {
        return new RaceRepository();
    }
}
