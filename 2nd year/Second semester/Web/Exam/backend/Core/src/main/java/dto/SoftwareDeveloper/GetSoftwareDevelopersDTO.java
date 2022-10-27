package dto.SoftwareDeveloper;


import entities.SoftwareDeveloper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSoftwareDevelopersDTO {
    List<SoftwareDeveloper> softwareDevelopers;
}
