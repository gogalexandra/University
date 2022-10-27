package server.repository.DBpersistence;

import common.domain.Race;
import common.domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class RaceDBRepository implements Repository<Integer, Race> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Race> findOne(Integer integer) {
        if (integer == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "select * from race where id = " + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            String location = rs.getString("location");
            String date = rs.getString("date");
            Race race = new Race(integer, location, date);
            return race;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Race> findAll() {
        String sql = "select * from race";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String location = rs.getString("location");
            String date = rs.getString("date");
            Race race = new Race(id, location, date);
            return race;
        });
    }

    @Override
    public Optional<Race> save(Race entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "insert into race (id, location, date) values (?, ?, ?)";
        try{
            Integer success = jdbcOperations.update(sql, entity.getId(), entity.getLocation(), entity.getDate());
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
    public Optional<Race> delete(Integer integer) {
        Optional<Race> toRemove = findOne(integer);
        String sql = "delete from race where id = ?";
        toRemove.ifPresent(race->jdbcOperations.update(sql, integer));
        return toRemove;
    }

    @Override
    public Optional<Race> update(Race entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "update race set location = ?, date = ? where id = ?";
        try{
            Integer success = jdbcOperations.update(sql, entity.getLocation(), entity.getDate(), entity.getId());
            if(success.equals(1)){
                return Optional.empty();
            }
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.of(entity);
        }
    }
}
