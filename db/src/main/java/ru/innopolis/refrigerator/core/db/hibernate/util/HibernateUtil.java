package ru.innopolis.refrigerator.core.db.hibernate.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.innopolis.refrigerator.core.logger.LogHandler;

import java.io.File;

/**
 *         Фабрика по созданию соединений для подключения к БД c помощью
 *         hibernate.
 */
public class HibernateUtil {

	private LogHandler logger = new LogHandler();

	private static SessionFactory sessionFactory = new Configuration().configure("file:/opt/git/Refrigerator2/db/src/main/resources/hibernate.cfg.xml")
			.buildSessionFactory();

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @file
	 */
	public static SessionFactory setSessionFactory(String file) {
		if (sessionFactory != null) {
			shutdown();
		}
		sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}
