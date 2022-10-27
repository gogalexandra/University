package entities;

import dto.driver.AddDriverDTO;
import dto.team.AddTeamDTO;
import dto.team.TeamDTO;
import dto.team.UpdateTeamDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

    public Team(AddTeamDTO dto) {
        this.name = dto.getName();
    }

    public Team(UpdateTeamDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", drivers=";
    }
}
