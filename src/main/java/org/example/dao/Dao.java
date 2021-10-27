package org.example.dao;

public interface Dao<I,E> {
    void create(E entity);
    E get(I id);
    void update(E update);
    void delete(I id);
}
