package ru.innopolis.refrigerator.core.db.hibernate.element;

import java.util.Collection;

public interface ElementDAO<E> {

    // public void addElement(E el);

    public E addElement(E el);

    public void deleteElement(E el);

    public Collection<E> getAllElements();

    public E getElementByID(Long elId);

    public void updateElement(E el);
}
