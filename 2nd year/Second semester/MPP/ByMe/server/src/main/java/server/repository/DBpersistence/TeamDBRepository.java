package server.repository.DBpersistence;

import common.domain.Team;
import common.domain.validators.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;


import java.util.Optional;

public class TeamDBRepository implements Repository<Integer, Team> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Optional<Team> findOne(Integer integer) {
        if (integer == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        String sql = "select * from team where id = " + integer;
        return jdbcOperations.query(sql, (rs, i) -> {
            String name = rs.getString("name");
            Integer teamPrincipalId = rs.getInt("teamprincipalid");
            Team team = new Team(integer, name, teamPrincipalId);
            return team;
        }).stream().findFirst();    }

    @Override
    public Iterable<Team> findAll() {
        String sql = "select * from team";
        return jdbcOperations.query(sql, (rs, i) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            Integer teamPrincipalId = rs.getInt("teamprincipalid");
            return new Team(id, name, teamPrincipalId);
        });
    }

    @Override
    public Optional<Team> save(Team entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "insert into team (id, name, teamprincipalid) values (?, ?, ?)";
        try{
            Integer success = jdbcOperations.update(sql, entity.getId(), entity.getName(), entity.getTeamPrincipalId());
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
    public Optional<Team> delete(Integer integer) {
        Optional<Team> toRemove = findOne(integer);
        String sql = "delete from team where id = ?";
        toRemove.ifPresent(team->jdbcOperations.update(sql, integer));
        return toRemove;
    }

    @Override
    public Optional<Team> update(Team entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        String sql = "update team set name = ?, teamprincipalid = ? where id = ?";
        try{
            Integer success = jdbcOperations.update(sql, entity.getName(), entity.getTeamPrincipalId(), entity.getId());
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
