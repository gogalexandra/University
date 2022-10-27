package dto.Project;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProjectDTO {
    Integer ProjectManagerID;
    String name;
    String description;
    String members;
}
