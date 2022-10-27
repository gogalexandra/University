package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "race")
@Getter
@Setter
@ToString
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Race(String name) {
        this.name = name;
    }

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Participation> participations;

    public Race() {

    }
}
