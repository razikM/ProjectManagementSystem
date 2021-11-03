package org.example.dao;

import java.util.List;

public interface Dao<I,E> {
    void create(E entity);
    E get(I id);
    void update(E update);
    void delete(I id);
    List<E> getAll();
}
