package config;

import common.Constants;
import domain.Driver;
import domain.Participation;
import domain.Race;
import domain.Team;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.*;
import ui.Console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean
    public DriverService genreMovieService() {
        return new DriverService(getExecutorsService(), client());
    }

    @Bean
    public ParticipationService genreService() {
        return new ParticipationService(getExecutorsService(), client());
    }

    @Bean
    public RaceService movieService() {
        return new RaceService(getExecutorsService(), client());
    }

    @Bean
    public TeamService ratingService() {
        return new TeamService(getExecutorsService(), client());
    }

    @Bean
    public Client client() {
        return new Client(Constants.HOST, Constants.PORT);
    }

    @Bean
    public ExecutorService getExecutorsService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
}
