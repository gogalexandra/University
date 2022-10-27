package entities;

import dto.participation.AddParticipationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int team;
    private int race;

    public Participation(AddParticipationDTO dto) {
        this.team = dto.getTeamId();
        this.race = dto.getRaceId();
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id='" + id + '\'' +
                "team=" + team +
            ", race=" + race +
            '}';
    }
}
