package ru.innopolis.refrigerator.core.db.dao.session;

import ru.innopolis.refrigerator.core.db.hibernate.element.ElementDAO;

public interface SessionDAO<E> extends ElementDAO<E>, SpecialSessionDAO {

}
