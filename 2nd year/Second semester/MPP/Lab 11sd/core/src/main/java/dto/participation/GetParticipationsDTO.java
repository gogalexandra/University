package dto.participation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import entities.Participation;
import entities.Race;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetParticipationsDTO implements Serializable {
    List<Participation> participations;
}
