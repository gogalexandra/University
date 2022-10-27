package dto.race;

import entities.Driver;
import entities.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetRacesDTO implements Serializable {
    List<Race> races;
}
