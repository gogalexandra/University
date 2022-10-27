package repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface Repository<T> {

    List<T> findAll();

    Optional<T> findOne(int id);

    void save (T entity);

    void update(T entity);

    void deleteById(int id);
}
