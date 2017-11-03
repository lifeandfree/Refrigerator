package ru.innopolis.refrigerator.core.db.hibernate.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.refrigerator.core.logger.LogHandler;

import java.io.File;

/**
 *         Фабрика по созданию соединений для подключения к БД c помощью
 *         hibernate.
 */
//@Repository
public class HibernateUtil {

	private static final Logger logger = LogManager.getLogger(HibernateUtil.class.getName());

//	@Autowired
//	private static SessionFactory sessionFactory;

//	private static SessionFactory sessionFactory = new Configuration().configure("file:/opt/git/Refrigerator2/db/src/main/resources/hibernate.cfg.xml")
//			.buildSessionFactory();


//	public static SessionFactory getSessionFactory() {
//		return sessionFactory;
//	}
//
//	public static void setSessionFactory(SessionFactory sessionFactory) {
//		HibernateUtil.sessionFactory = sessionFactory;
//	}

	/**
	 * @file
	 */
//	public static SessionFactory setSessionFactory(String file) {
//		if (sessionFactory != null) {
//			shutdown();
//		}
//		sessionFactory = new Configuration().configure(new File("hibernate.cfg.xml")).buildSessionFactory();
//		return sessionFactory;
//	}

//	public void shutdown() {
//		getSessionFactory().close();
//	}
}
