package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "participation")
@Getter
@Setter
@ToString(doNotUseGetters = true)
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "\"teamId\"")
    @JsonBackReference(value = "participation-team")
    @ToString.Exclude
    private Team team;

    @ManyToOne
    @JoinColumn(name = "\"teamId\"")
    @JsonBackReference(value = "participation-race")
    @ToString.Exclude
    private Race race;

    public Participation(Team team, Race race, Integer points) {
        this.team = team;
        this.race = race;
        this.points = points;
    }

    public Participation() {

    }

    @Transient
    Integer teamId;

    @Transient
    Integer raceId;

    @JsonProperty("teamId")
    public Integer getTeamId() {
        return team.getId();
    }

    @JsonProperty("raceId")
    public Integer getRaceId() {
        return race.getId();
    }
}
