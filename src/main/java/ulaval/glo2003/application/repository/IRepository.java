package ulaval.glo2003.application.repository;

import java.util.ArrayList;

public interface IRepository<T> {

    void save(T entity);

    void remove(T entity);

    void deleteAll();

    void update(T entity);

    ArrayList<T> findAll();
}
