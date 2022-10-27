package common;

import common.domain.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityConverter {
    public static String stringifyDriver(Driver driver){
        return driver.getId() + "," +
                driver.getName() + "," +
                driver.getExperience() + "," +
                driver.getCarId() + "," +
                driver.getTeamId();
    }

    public static Driver destringifyDriver(String stringDriver){
        String[] tokens = stringDriver.split(",");
        Integer id = Integer.valueOf(tokens[0]);
        String name = tokens[1];
        Integer experience = Integer.valueOf(tokens[2]);
        Integer carid = Integer.valueOf(tokens[3]);
        Integer teamid = Integer.valueOf(tokens[4]);
        Driver driver = new Driver(id, name, experience, carid, teamid);
        return driver;
    }

    public static String stringifyCar(Car car){
        return car.getId() + "," +
                car.getName() + "," +
                car.getHorsepower() + "," +
                car.getMaxSpeed();
    }

    public static Car destringifyCar(String stringCar){
        String[] tokens = stringCar.split(",");
        Integer id = Integer.valueOf(tokens[0]);
        String name = tokens[1];
        Integer horsepower = Integer.valueOf(tokens[2]);
        Integer maxspeed = Integer.valueOf(tokens[3]);
        Car car = new Car(id, name, horsepower, maxspeed);
        return car;
    }

    public static String stringifyRace(Race race){
        return race.getId() + "," +
                race.getLocation() + "," +
                race.getDate();
    }

    public static Race destringifyRace(String stringRace) {
            String[] tokens = stringRace.split(",");
            Integer id = Integer.valueOf(tokens[0]);
            String location = tokens[1];
            String date = tokens[2];
            Race race = new Race(id, location, date);
            return race;
    }

    public static String stringifyParticipation(Participation participation){
        return participation.getId() + "," +
                participation.getTeam() + "," +
                participation.getRace();
    }

    public static Participation destringifyParticipation(String stringParticipation){
        String[] tokens = stringParticipation.split(",");
        Integer id = Integer.valueOf(tokens[0]);
        Integer teamid = Integer.valueOf(tokens[1]);
        Integer raceid = Integer.valueOf(tokens[2]);
        Participation participation = new Participation(id, teamid, raceid);
        return participation;
    }

    public static String stringifyTeam(Team team) {
        return team.getId() + "," +
                team.getName() + "," +
                team.getTeamPrincipalId();
    }

    public static Team destringifyTeam(String stringTeam){
        String[] tokens = stringTeam.split(",");
        Integer id = Integer.valueOf(tokens[0]);
        String name = tokens[1];
        Integer teamprincipalid = Integer.valueOf(tokens[2]);
        Team team = new Team(id, name, teamprincipalid);
        return team;
    }
}
