package domain;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseEntity<Integer> {
    private String name;
    private List<Integer> drivers;


    public Team(String name, List<Integer> drivers) {
        this.name = name;
        this.drivers = drivers;
    }

    public Team(Integer id, String name) {
        setId(id);
        this.name = name;
        drivers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Integer> getDrivers() {
        return drivers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrivers(ArrayList<Integer> drivers) {
        this.drivers = drivers;
    }
    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", drivers=" + drivers +
                '}';
    }
}
