package repository;

import domain.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.Optional;

public class DriverRepository implements Repository<Driver> {

    @Autowired
    private JdbcOperations jdbcOperations;

    public DriverRepository(JdbcOperations jdbc) {
        jdbcOperations = jdbc;
    }

    public DriverRepository() {
    }

    @Override
    public List<Driver> findAll() {
        String sql = "select * from driver";
        return jdbcOperations.query(sql, (rs, i) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int experience = rs.getInt("experience");
            return new Driver(id, name, experience);
        });
    }

    @Override
    public Optional<Driver> findOne(int id) {
        String sql = "select * from driver where id = " + id;
        return Optional.ofNullable(jdbcOperations.query(sql, (rs, i) -> {
            int id_ = rs.getInt("id");
            String name = rs.getString("name");
            int experience = rs.getInt("experience");
            return new Driver(id, name, experience);
        }).get(0));
    }

    @Override
    public void save(Driver entity) {
        String sql = "insert into driver (id, name, experience) values (?, ?, ?)";
        jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getExperience());
    }

    @Override
    public void update(Driver entity) {
        String sql = "update driver set name = ?, experience = ? where id = ?";
        jdbcOperations.update(sql, entity.getName(), entity.getExperience(), entity.getId());
    }

    @Override
    public void deleteById(int id) {
        String sql = "delete from driver where id =?";
        jdbcOperations.update(sql, id);
    }
}
