package repository;

import domain.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class RaceRepository implements Repository<Race> {

    @Autowired
    private JdbcOperations jdbcOperations;

    public RaceRepository(JdbcOperations jdbc) {
        this.jdbcOperations = jdbc;
    }

    public RaceRepository() {

    }

    @Override
    public List<Race> findAll() {
        String sql = "select * from race";
        return jdbcOperations.query(sql, (rs, i) -> {
            int id = rs.getInt("id");
            String location = rs.getString("location");
            Date date = rs.getDate("date");
            return new Race(id, location, date);
        });
    }

    @Override
    public Optional<Race> findOne(int id) {
        String sql = "select * from race where id = " + id;
        return Optional.ofNullable(jdbcOperations.query(sql, (rs, i) -> {
            int id_ = rs.getInt("id");
            String location = rs.getString("location");
            Date date = rs.getDate("date");
            return new Race(id, location, date);
        }).get(0));
    }

    @Override
    public void save(Race entity) {
        String sql = "insert into Race (id, location, date) values (?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getLocation(), entity.getDate());
    }

    @Override
    public void update(Race entity) {
        String sql = "update race set location = ?, date = ? where id = ?";
        jdbcOperations.update(sql, entity.getLocation(), entity.getDate(), entity.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from race where id =?";
        jdbcOperations.update(sql, id);
    }
}
