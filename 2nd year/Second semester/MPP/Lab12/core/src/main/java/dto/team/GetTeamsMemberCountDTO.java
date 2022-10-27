package dto.team;

import entities.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetTeamsMemberCountDTO implements Serializable {
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static public class InnerTeam {
        Integer id;
        String name;
        Integer memberCount;
    }

    List<InnerTeam> teams;
}
