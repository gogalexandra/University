package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Set;

@Entity(name = "team")
@Getter
@Setter
@ToString
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Driver> drivers;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Participation> participations;

    public Team(String name) {
        this.name = name;
    }

    public Team() {

    }
}
