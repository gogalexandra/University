package repository;

import entities.Driver;
import entities.Team;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    List<Driver> findAll();

    Driver findDriverById(Integer id);

    void deleteDriverById(Integer id);

    List<Driver> findAllByTeam(Integer teamId);

    List<Driver> findByOrderByNameAsc();
}
