package ulaval.glo2003.application.repository;

import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;

import java.util.ArrayList;

public interface IRepository<T> {

    void save(T entity);

    void remove(T entity);

    void deleteAll();

    void update(T entity);

    ArrayList<T> findAll();

    int count();
}
