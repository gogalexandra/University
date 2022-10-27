package server.repository.DBpersistence;

import common.domain.Participation;
import common.domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.Optional;

public class ParticipationDBRepository implements Repository<Integer, Participation> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Participation> findOne(Integer integer) {

        if (integer == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "select * from participation where id = " + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer teamId = rs.getInt("teamid");
            Integer raceId = rs.getInt("raceid");
            Participation participation = new Participation(integer, teamId, raceId);
            return participation;
        }).stream().findFirst();
    }

    @Override
    public Iterable<Participation> findAll() {
        String sql = "select * from participation";
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            Integer teamId = rs.getInt("teamid");
            Integer raceId = rs.getInt("raceid");
            Participation participation = new Participation(id, teamId, raceId);
            return participation;
        });
    }

    @Override
    public Optional<Participation> save(Participation entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "insert into participation (id, teamid, raceid) values (?, ?, ?)";
        try{
            Integer success = jdbcOperations.update(sql, entity.getId(), entity.getTeam(), entity.getRace());
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
    public Optional<Participation> delete(Integer integer) {
        Optional<Participation> toRemove = findOne(integer);
        String sql = "delete from participation where id = ?";
        toRemove.ifPresent(participation->jdbcOperations.update(sql, integer));
        return toRemove;
    }

    @Override
    public Optional<Participation> update(Participation entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "update participation set teamid = ?, raceid = ? where id = ?";
        try{
            Integer success = jdbcOperations.update(sql, entity.getTeam(), entity.getRace(), entity.getId());
            if(success.equals(1)){
                return Optional.empty();
            }
            return Optional.of(entity);
        }
        catch (Exception e){
            return Optional.of(entity);
        }
    }

    public Iterable<Participation> getParticipationTeam(Integer teamId){
        String sql = "select * from participation where teamid = "+ teamId;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            Integer raceId = rs.getInt("raceid");
            Participation participation = new Participation(id, teamId, raceId);
            return participation;
        });
    }

    public Iterable<Participation> getParticipationRace(Integer raceId){
        String sql = "select * from participation where raceid = "+ raceId;
        return jdbcOperations.query(sql, (rs, i) -> {
            Integer id = rs.getInt("id");
            Integer teamid = rs.getInt("teamid");
            Participation participation = new Participation(id, teamid, raceId);
            return participation;
        });
    }
}