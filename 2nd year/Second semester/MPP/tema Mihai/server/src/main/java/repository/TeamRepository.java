package repository;

import domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;

public class TeamRepository implements Repository<Team> {

    @Autowired
    private JdbcOperations jdbcOperations;

    public TeamRepository(JdbcOperations jdbc) {
        jdbcOperations = jdbc;
    }

    public TeamRepository() {

    }

    @Override
    public List<Team> findAll() {
        String sql = "select * from team";
        return jdbcOperations.query(sql, (rs, i) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            return new Team(id, name);
        });
    }

    @Override
    public Optional<Team> findOne(int id) {
        String sql = "select * from team where id = " + id;
        return Optional.ofNullable(jdbcOperations.query(sql, (rs, i) -> {
            int id_ = rs.getInt("id");
            String name = rs.getString("name");
            return new Team(id, name);
        }).get(0));
    }

    @Override
    public void save(Team entity) {
        String sql = "insert into team (id, name) values (?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getName());
    }

    @Override
    public void update(Team entity) {
        String sql = "update team set name = ? where id = ?";
        jdbcOperations.update(sql, entity.getName(), entity.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from team where id =?";
        jdbcOperations.update(sql, id);
    }
}
