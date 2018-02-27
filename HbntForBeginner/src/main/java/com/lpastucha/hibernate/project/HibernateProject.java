package com.lpastucha.hibernate.project;

import java.util.Date;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import com.lpastucha.hibernate.project.model.Employee;
import com.lpastucha.hibernate.project.model.Employee1;
import com.lpastucha.hibernate.project.util.HibernateUtil;

/**
 * 
 * 
 * Created on 2018-02-26
 *
 * @author lpastucha
 *
 */
public class HibernateProject {

	private static final Logger LOG = Logger.getLogger(HibernateProject.class);

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		runXmlConfig();
	}

	public static void runXmlConfig() {
		Employee employee = new Employee();
		employee.setName("lpastucha");
		employee.setRole("CEO");
		employee.setInsertDate(new Date());

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(employee);
		session.getTransaction().commit();
		LOG.info(employee);
		session.close();
	}

	public static void runAnnotationConfig() {
		Employee1 employee1 = new Employee1();
		employee1.setName("lpastucha");
		employee1.setRole("CEO");
		employee1.setInsertDate(new Date());

		Session session = HibernateUtil.getSessionAnnotationFactory().getCurrentSession();
		session.beginTransaction();
		session.save(employee1);
		session.getTransaction().commit();
		LOG.info(employee1);
		session.close();
	}

	public static void runJavaConfig() {
		Employee1 employee1 = new Employee1();
		employee1.setName("lpastucha");
		employee1.setRole("CEO");
		employee1.setInsertDate(new Date());

		Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
		session.beginTransaction();
		session.save(employee1);
		session.getTransaction().commit();
		LOG.info(employee1);
		session.close();
	}

}
