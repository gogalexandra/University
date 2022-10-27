package service;

import repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public interface Service<T> {
    List<T> getAll();

    Optional<T> getById(int id);

    void add(T entity);

    void update(T entity);

    void delete(int id);

    Repository<T> getRepository();
}
