package ru.innopolis.refrigerator.core.db.hibernate.element;

import java.util.Collection;

public interface ElementDAO<E> {

    public E add(E el) throws Exception;

    public E delete(E el) throws Exception;

    public Collection<E> getAll() throws Exception;

    public E getById(Long elId) throws Exception;

    public E update(E el) throws Exception;

    public Collection<E> addAll(Collection<E> els) throws Exception;

}
