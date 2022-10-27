package entities;

import dto.Project.AddProjectDTO;
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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Integer ProjectManagerID;
    private String name;
    private String description;
    private String members;

    public Project(AddProjectDTO dto){
        name = dto.getName();
        ProjectManagerID = dto.getProjectManagerID();
        name = dto.getName();
        description = dto.getDescription();
        members = dto.getMembers();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", ProjectManagerID=" + ProjectManagerID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", members='" + members + '\'' +
                '}';
    }
}