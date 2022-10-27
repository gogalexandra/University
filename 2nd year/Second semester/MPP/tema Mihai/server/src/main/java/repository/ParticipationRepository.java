package repository;

import domain.Participation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;

public class ParticipationRepository implements Repository<Participation> {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public List<Participation> findAll() {
        String sql = "select * from participation";
        return jdbcOperations.query(sql, (rs, i) -> {
            int id = rs.getInt("id");
            int teamId = rs.getInt("teamid");
            int raceId = rs.getInt("raceid");
            return new Participation(id, raceId, teamId);
        });    }

    @Override
    public Optional<Participation> findOne(int id) {
        String sql = "select * from participation where id = " + id;
        return Optional.ofNullable(jdbcOperations.query(sql, (rs, i) -> {
            int id_ = rs.getInt("id");
            int teamId = rs.getInt("teamid");
            int raceId = rs.getInt("raceid");
            return new Participation(id, raceId, teamId);
        }).get(0));
    }

    @Override
    public void save(Participation entity) {
        String sql = "insert into participation (id, raceid, teamid) values (?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getRace(), entity.getTeam());
    }

    @Override
    public void update(Participation entity) {
        String sql = "update participation set teamid = ? , raceid = ? where id = ?";
        jdbcOperations.update(sql, entity.getTeam(), entity.getRace(), entity.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from participation where id =?";
        jdbcOperations.update(sql, id);
    }
}
