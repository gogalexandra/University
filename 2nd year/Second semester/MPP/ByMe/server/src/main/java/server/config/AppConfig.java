package server.config;

import common.F1Service;
import common.domain.*;
import server.repository.DBpersistence.*;
import server.repository.DBpersistence.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import server.service.*;
import server.service.asyncservices.ServerF1Service;
import server.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    Repository<Integer, Driver> driverRepository() {
        return new DriverDBRepository();
    }

    @Bean
    Repository<Integer, Car> carRepository() {
        return new CarDBRepository();
    }
    @Bean
    Repository<Integer, Participation> participationRepository() {
        return new ParticipationDBRepository();
    }
    @Bean
    Repository<Integer, Race> raceRepository() {
        return new RaceDBRepository();
    }
    @Bean
    Repository<Integer, Team> teamRepository() {
        return new TeamDBRepository();
    }

    @Bean
    DriverService driverService(){
        return new DriverService(driverRepository());
    }

    @Bean
    CarService carService(){
        return new CarService(carRepository(), driverRepository());
    }

    @Bean
    ParticipationService participationService(){
        return new ParticipationService(participationRepository());
    }

    @Bean
    TeamService teamService(){
        return new TeamService(teamRepository(), participationRepository(), driverRepository());
    }

    @Bean
    RaceService raceService(){
        return new RaceService(raceRepository(), participationRepository());
    }

    @Bean
    ExecutorService executorService(){return  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());}

    @Bean
    TcpServer tcpServer(){return new TcpServer(executorService(), F1Service.PORT);}

    @Bean
    F1Service getPetShopService(){return new ServerF1Service(executorService(), driverService(), carService(), participationService(), teamService(), raceService());
    }
}
