package com.lpastucha.hibernate.project.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger;

import com.lpastucha.hibernate.project.model.Employee;
import com.lpastucha.hibernate.project.model.Employee1;

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

	private static SessionFactory sessionFactory;

	private static SessionFactory sessionAnnotationFactory;

	private static SessionFactory sessionJavaConfigFactory;

	/**
	 * Gets session factory initilized by xml file configuration
	 *
	 * @return configured session factory
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null)
			sessionFactory = buildSessionFactory();
		return sessionFactory;
	}

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			LOG.info("Hibernate Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			LOG.info("Hibernate serviceRegistry created");
			configuration.addAnnotatedClass(Employee.class);
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable e) {
			LOG.error("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Gets session factory for annotation configuration
	 *
	 * @return configured by annotations session factory
	 */
	public static SessionFactory getSessionAnnotationFactory() {
		if (sessionAnnotationFactory == null)
			sessionAnnotationFactory = buildSessionAnnotationFactory();
		return sessionAnnotationFactory;
	}

	private static SessionFactory buildSessionAnnotationFactory() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate-annotation.cfg.xml");
			LOG.info("Hibernate Annotation Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			LOG.info("Hibernate Annotation serviceRegistry created");
			configuration.addAnnotatedClass(Employee1.class);
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable e) {
			LOG.error("Initial Annotation SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Gets session factory configured by Java Config
	 *
	 * @return configured with Java Config session factory
	 */
	public static SessionFactory getSessionJavaConfigFactory() {
		if (sessionJavaConfigFactory == null)
			sessionJavaConfigFactory = buildSessionJavaConfigFactory();
		return sessionJavaConfigFactory;
	}

	private static SessionFactory buildSessionJavaConfigFactory() {
		try {
			Configuration configuration = new Configuration();
			Properties props = new Properties();
			props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			props.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate_project");
			props.put("hibernate.connection.username", "root");
			props.put("hibernate.connection.password", "lauraifilon4113");
			props.put("hibernate.current_session_context_class", "thread");
			configuration.addProperties(props);
			LOG.info("Hibernate Java Config Configuration loaded");
			configuration.addAnnotatedClass(Employee1.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			LOG.info("Hibernate Java Config serviceRegistry created");
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			return sessionFactory;
		} catch (Throwable e) {
			LOG.error("Initial Annotation SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);

		}
	}
}
