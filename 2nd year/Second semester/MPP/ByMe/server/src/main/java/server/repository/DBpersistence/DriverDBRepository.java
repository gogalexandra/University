package server.repository.DBpersistence;

import common.domain.Driver;
import common.domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class DriverDBRepository implements Repository<Integer, Driver> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Driver> findOne(Integer integer) {
        if (integer == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "select * from driver where id = " + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            String name = rs.getString("name");
            Integer experience = rs.getInt("experience");
            Integer carid = rs.getInt("carid");
            Integer teamid = rs.getInt("teamid");
            Driver driver = new Driver(integer, name, experience, carid, teamid);
            return driver;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Driver> findAll() {
        String sql = "select * from driver";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Integer experience = rs.getInt("experience");
            Integer carid = rs.getInt("carid");
            Integer teamid = rs.getInt("teamid");
            Driver driver = new Driver(id, name, experience, carid, teamid);
            return driver;
        });
    }

    @Override
    public Optional<Driver> save(Driver entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "insert into driver (id, name, experience, carid, teamid) values (?, ?, ?, ?, ?)";
        try{
            Integer success = jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getExperience(), entity.getCarId(), entity.getTeamId());
            if(success.equals(1)){
                return Optional.empty();
            }
            return Optional.of(entity);
        }
        catch (Exception e)
        {
            return Optional.of(entity);
        }
    }

    @Override
    public Optional<Driver> delete(Integer integer) {
        Optional<Driver> toRemove = findOne(integer);
        String sql = "delete from driver where id = ?";
        toRemove.ifPresent(driver->jdbcOperations.update(sql, integer));
        return toRemove;
    }

    @Override
    public Optional<Driver> update(Driver entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "update driver set name = ?, experience = ?, carid = ?, teamid = ? where id = ?";
        try{
            Integer success = jdbcOperations.update(sql, entity.getName(), entity.getExperience(), entity.getCarId(), entity.getTeamId(), entity.getId());
            if(success.equals(1)){
                return Optional.empty();
            }
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.of(entity);
        }
    }

    public Iterable<Driver> getDriversCar(Integer carId){
        String sql = "select * from driver where carid = "+ carId;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Integer experience = rs.getInt("experience");
            Integer teamid = rs.getInt("teamid");
            Driver driver = new Driver(id, name, experience, carId, teamid);
            return driver;
        });
    }

    public Iterable<Driver> getTeamDrivers(Integer teamId){
        String sql = "select * from driver where teamid = "+ teamId;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Integer experience = rs.getInt("experience");
            Integer carid = rs.getInt("carid");
            Driver driver = new Driver(id, name, experience, carid, teamId);
            return driver;
        });
    }
}