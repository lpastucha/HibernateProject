package com.lpastucha.hibernate.project.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

/**
 * 
 * 
 * Created on 2018-02-26
 *
 * @author lpastucha
 *
 */
public class HibernateUtil {

	private static final Logger LOG = Logger.getLogger(HibernateUtil.class);

	private SessionFactory sessionFactory;

	private SessionFactory sessionAnnotationFactory;

	private SessionFactory sessionJavaConfigFactory;

	private static SessionFactory buildSesionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			LOG.info("Hibernate Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			LOG.info("Hibernate serviceRegistry created");
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable e) {
			LOG.error("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}
}
