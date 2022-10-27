package tcp;

import com.google.gson.Gson;
import common.Message;
import domain.Driver;
import domain.Participation;
import domain.Race;
import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import service.DriverService;
import service.ParticipationService;
import service.RaceService;
import service.TeamService;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class ApplicationServer extends TcpServer{

    @Autowired
    private DriverService driverService;

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private TeamService teamService;

    public ApplicationServer(ExecutorService executorService, int port) {
        super(executorService, port);
        setupHandlers();
    }

    private void setupHandlers() {
        driverHandlers();
        teamHandlers();
        raceHandlers();
        participationHandlers();
    }

    private void driverHandlers() {
        addHandler("add-driver", (request) -> {
            Driver driver = new Gson().fromJson(request.getBody(), Driver.class);
            driverService.add(driver);
            return new Message(Message.OK, "ok");
        });

        addHandler("delete-driver", (request) -> {
            Driver driver = new Gson().fromJson(request.getBody(), Driver.class);
            driverService.delete(driver.getId());
            return new Message(Message.OK, "ok");
        });

        addHandler("update-driver", (request) -> {
            Driver driver = new Gson().fromJson(request.getBody(), Driver.class);
            driverService.update(driver);
            return new Message(Message.OK, "ok");
        });

        addHandler("get-driver", (request) -> {
            List<Driver> list = driverService.getAll();
            return new Message(Message.OK, new Gson().toJson(list));
        });
    }

    private void teamHandlers() {
        addHandler("add-team", (request) -> {
            Team team = new Gson().fromJson(request.getBody(), Team.class);
            try {
                teamService.add(team);
                return new Message(Message.OK, "ok");
            } catch (RuntimeException e) {
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        addHandler("delete-team", (request) -> {
            Team team = new Gson().fromJson(request.getBody(), Team.class);
            teamService.delete(team.getId());
            return new Message(Message.OK, "ok");
        });

        addHandler("update-team", (request) -> {
            Team team = new Gson().fromJson(request.getBody(), Team.class);
            try {
                teamService.update(team);
                return new Message(Message.OK, "ok");
            } catch (RuntimeException e) {
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        addHandler("get-team", (request) -> {
            List<Team> list = teamService.getAll();
            return new Message(Message.OK, new Gson().toJson(list));
        });
    }
    
    private void raceHandlers() {
        addHandler("add-race", (request) -> {
            Race race = new Gson().fromJson(request.getBody(), Race.class);
            raceService.add(race);
            return new Message(Message.OK, "ok");

        });

        addHandler("delete-race", (request) -> {
            Race race = new Gson().fromJson(request.getBody(), Race.class);
            raceService.delete(race.getId());
            return new Message(Message.OK, "ok");
        });

        addHandler("update-race", (request) -> {
            Race race = new Gson().fromJson(request.getBody(), Race.class);
            raceService.update(race);
            return new Message(Message.OK, "ok");

        });

        addHandler("get-race", (request) -> {
            List<Race> list = raceService.getAll();
            return new Message(Message.OK, new Gson().toJson(list));
        });
    }

    private void participationHandlers() {
        addHandler("add-participation", (request) -> {
            Participation participation = new Gson().fromJson(request.getBody(), Participation.class);
            try {
                participationService.add(participation);
                return new Message(Message.OK, "ok");
            } catch (RuntimeException e) {
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        addHandler("delete-participation", (request) -> {
            Participation participation = new Gson().fromJson(request.getBody(), Participation.class);
            participationService.delete(participation.getId());
            return new Message(Message.OK, "ok");
        });

        addHandler("update-participation", (request) -> {
            Participation participation = new Gson().fromJson(request.getBody(), Participation.class);
            try {
                participationService.update(participation);
                return new Message(Message.OK, "ok");
            } catch (RuntimeException e) {
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        addHandler("get-participation", (request) -> {
            List<Participation> list = participationService.getAll();
            return new Message(Message.OK, new Gson().toJson(list));
        });
    }
    
}
