package ru.innopolis.refrigerator.core.db.hibernate.element;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.refrigerator.core.db.hibernate.util.HibernateUtil;


public class ElementDAOImpl<E> implements ElementDAO<E> {

    private static final Logger logger = LogManager.getLogger(ElementDAOImpl.class.getName());

    private SessionFactory sessionFactory;
    private Class<E> elementClass;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ElementDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    public ElementDAOImpl() {
        super();
    }

    /**
     * Добавляет элемент в БД.
     *
     * @param el
     *            - элемент для добавления.
     */
    @Override
    public E add(E el) {
        Session session = null;
        try {
            //session = HibernateUtil.getSessionFactory().openSession();TODO
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(el);
            transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not add an item to the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    /**
     * Удаляет элемент в БД.
     *
     * @param el
     *            - элемент для удаления.
     */
    @Override
    public E delete(E el) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(el);
            transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not remove an item to the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    /**
     * Запращивает все элементы из БД.
     *
     * @return {@link Collection} элементов {@link E} из БД.
     */
    @Override
    public Collection<E> getAll() {
        List<E> els = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Transaction transaction = session.beginTransaction();
            // TODO
            DetachedCriteria criteria = DetachedCriteria.forClass(elementClass);
            els = criteria.getExecutableCriteria(session).list();

            // els = session.createCriteria(elementClass).list();
            // transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not query all items in the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return els;
    }

    /**
     * Запращивает элемент по Id.
     *
     * @param elId
     *            - элемент для запроса.
     * @return элемент {@link E} из БД.
     */
    public E getElementByID(long elId) {
        return getById(Long.valueOf(elId));
    }

    /**
     * Запращивает элемент по Id.
     *
     * @param elId
     *            - элемент для запроса.
     * @return элемент {@link E} из БД.
     */
    @Override
    public E getById(Long elId) {
        E el = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Transaction transaction = session.beginTransaction();
            el = session.get(elementClass, elId);
            // transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not query item in the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    /**
     * Обновляет элемент в БД.
     *
     * @param el
     *            - элемент для добавления.
     */
    @Override
    public E update(E el) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(el);
            transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not update an item to the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    @Override
    public Collection<E> addAll(Collection<E> els) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(els);
            transaction.commit();
        }
        catch (Exception e) {
            logger.error("I can not update an item to the database" + e.toString());
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return els;
    }

}
