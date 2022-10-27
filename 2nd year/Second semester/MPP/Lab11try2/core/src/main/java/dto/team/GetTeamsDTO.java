package dto.team;

import entities.Race;
import entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetTeamsDTO implements Serializable {
    List<Team> teams;
}
