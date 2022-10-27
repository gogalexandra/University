package entities;

import dto.SoftwareDeveloper.AddSoftwareDeveloperDTO;
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
public class SoftwareDeveloper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private Integer age;
    private String skills;

    public SoftwareDeveloper(AddSoftwareDeveloperDTO dto){
        name = dto.getName();
        age = dto.getAge();
        skills = dto.getSkills();
    }

    @Override
    public String toString() {
        return "SoftwareDeveloper{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", skills='" + skills + '\'' +
                '}';
    }
}