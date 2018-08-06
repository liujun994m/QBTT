package hust.ioic.oa.test;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.EJBException;
import javax.ejb.ScheduleExpression;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

import hust.ioic.oa.domain.Center;
import hust.ioic.oa.domain.Collection;
import hust.ioic.oa.domain.Operator;

import hust.ioic.oa.domain.Port;

import hust.ioic.oa.domain.Server;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHibernate {

	private SessionFactory sessionFactory = new Configuration()//
			.configure("hibernate.cfg.xml").buildSessionFactory();

	@Test
	public void testHibernate() throws Exception {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			session.save(new Server());

			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Test
	public void test() {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sf = (SessionFactory) ac.getBean("sessionFactory");

	}
}
