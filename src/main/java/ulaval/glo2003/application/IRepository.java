package ulaval.glo2003.application;

import ulaval.glo2003.domain.Product;

import java.util.ArrayList;

public interface IRepository<T> {

    void save(T entity);

    void remove(T entity);

    void deleteAll();

    T findById(String id);

    void update(T entity);

    ArrayList<T> findAll();
}
