package entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "driver")
@Getter
@Setter
@ToString
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "\"teamId\"")
    @JsonIgnore
    private Team team;

    public Driver(String name) {
        this.name = name;
    }

    public Driver() {

    }
}
