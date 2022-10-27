package dto.Project;


import entities.Project;
import entities.SoftwareDeveloper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectsDTO {
    List<Project> projects;
}
