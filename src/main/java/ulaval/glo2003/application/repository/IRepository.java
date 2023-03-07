package ulaval.glo2003.application;

import java.util.ArrayList;

public interface IRepository<T> {

    void save(T entity);

    void remove(T entity);

    void deleteAll();



    ArrayList<T> findAll();

    int count();
}
