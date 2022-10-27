package entities;

import dto.Journals.AddJournalDTO;
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
public class Journals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;

    public Journals(AddJournalDTO dto){
        name = dto.getName();
    }

    @Override
    public String toString() {
        return "Jurnals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}