package dto.driver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateDriverDTO {
    Integer id;
    String name;
    Integer experience;
    Integer team;
}
