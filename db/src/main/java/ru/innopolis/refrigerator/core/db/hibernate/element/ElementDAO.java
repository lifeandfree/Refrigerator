package ru.innopolis.refrigerator.core.db.hibernate.element;

import java.util.Collection;

public interface ElementDAO<E> {

    public E add(E el);

    public void delete(E el);

    public Collection<E> getAll();

    public E getById(Long elId);

    public void update(E el);

    public Collection<E> addAll(Collection<E> el);
}
