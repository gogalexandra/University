package repository;

import entities.Driver;
import entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    List<Participation> findByPointsGreaterThan(Integer points);
}
