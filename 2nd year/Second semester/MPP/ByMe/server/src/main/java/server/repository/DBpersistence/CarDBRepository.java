package server.repository.DBpersistence;

import common.domain.Car;
import common.domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class CarDBRepository implements Repository<Integer, Car> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Car> findOne(Integer integer) {
        if (integer == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "select * from car where id = " + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            String name = rs.getString("name");
            Integer horsepower = rs.getInt("horsepower");
            Integer maxspeed = rs.getInt("maxspeed");
            Car car = new Car(integer, name, horsepower, maxspeed);
            return car;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Car> findAll() {
        String sql = "select * from car";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            String name = rs.getString("name");
            Integer horsepower = rs.getInt("horsepower");
            Integer maxspeed = rs.getInt("maxspeed");
            Car car = new Car(id, name, horsepower, maxspeed);
            return car;
        });
    }

    @Override
    public Optional<Car> save(Car entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "insert into car (id, name, horsepower, maxspeed) values (?, ?, ?, ?)";
        try{
            Integer success = jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getHorsepower(), entity.getMaxSpeed());
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
    public Optional<Car> delete(Integer integer) {
        Optional<Car> toRemove = findOne(integer);
        String sql = "delete from car where id = ?";
        toRemove.ifPresent(car->jdbcOperations.update(sql, integer));
        return toRemove;
    }

    @Override
    public Optional<Car> update(Car entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "update car set name = ?, horsepower = ?, maxspeed = ? where id = ?";
        try{
            Integer success = jdbcOperations.update(sql, entity.getName(), entity.getHorsepower(), entity.getMaxSpeed(), entity.getId());
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