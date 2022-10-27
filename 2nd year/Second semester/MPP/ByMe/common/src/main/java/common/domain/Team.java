package common.domain;

import java.util.ArrayList;
import java.util.List;

public class Team extends BaseEntity<Integer> {
    private String name;
    private Integer teamPrincipalId;


    public Team(String name, Integer teamPrincipalId) {
        this.name = name;
        this.teamPrincipalId = teamPrincipalId;
    }

    public Team(Integer id, String name, Integer teamPrincipalId) {
        setId(id);
        this.name = name;
        this.teamPrincipalId = teamPrincipalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeamPrincipalId() {
        return teamPrincipalId;
    }

    public void setTeamPrincipalId(Integer teamPrincipalId) {
        this.teamPrincipalId = teamPrincipalId;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", teamPrincipal=" + teamPrincipalId +
                '}';
    }
}
