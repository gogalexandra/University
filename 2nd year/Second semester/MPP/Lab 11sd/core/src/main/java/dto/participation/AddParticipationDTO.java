package dto.participation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddParticipationDTO implements Serializable {
    Integer teamId;
    Integer raceId;
    Integer points;
}
