package entities;

import dto.driver.AddDriverDTO;
import dto.driver.DriverDTO;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private Integer experience;

    @Nullable
    private Integer team;

    public Driver(AddDriverDTO dto) {
        name = dto.getName();
        experience = dto.getExperience();
        team = dto.getTeam();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", team=" + team +
                '}';
    }
}
