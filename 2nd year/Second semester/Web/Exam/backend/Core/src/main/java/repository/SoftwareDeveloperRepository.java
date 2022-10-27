package repository;

import entities.Project;
import entities.SoftwareDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareDeveloperRepository extends JpaRepository<SoftwareDeveloper, Integer> {
    SoftwareDeveloper findSoftwareDeveloperByName(String name);
}
