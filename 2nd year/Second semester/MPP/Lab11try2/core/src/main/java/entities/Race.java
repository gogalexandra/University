package entities;

import dto.race.AddRaceDTO;
import dto.race.UpdateRaceDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Race{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String location;
    private String date;

    public Race(AddRaceDTO dto) {
        this.location = dto.getLocation();
        this.date = dto.getDate();
    }

    public Race(UpdateRaceDTO dto) {
        this.id = dto.getId();
        this.location = dto.getLocation();
        this.date = dto.getDate();
    }


    @Override
    public String toString() {
        return "Race{" +
                "id='" + id + '\'' +
                "location='" + location + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(getId(), race.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
