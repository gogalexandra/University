package repository;

import entities.Journals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalsRepository extends JpaRepository<Journals, Integer> {
    Journals findJournalsByName(String name);
}
