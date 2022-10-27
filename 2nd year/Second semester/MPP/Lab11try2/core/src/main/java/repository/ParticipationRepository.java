package repository;

import entities.Participation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    List<Participation> findAll();

    Participation findParticipationById(Integer id);

    void deleteById(Integer id);

    List<Participation> findParticipationByTeam(Integer teamId);

    List<Participation> findParticipationByRace(Integer raceId);
}
