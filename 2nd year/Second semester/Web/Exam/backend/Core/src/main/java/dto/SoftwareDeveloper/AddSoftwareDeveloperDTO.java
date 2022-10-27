package dto.SoftwareDeveloper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSoftwareDeveloperDTO {
    String name;
    Integer age;
    String skills;
}
