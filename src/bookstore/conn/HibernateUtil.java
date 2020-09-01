package bookstore.conn;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import bookstore.javabeans.Book;
import bookstore.javabeans.Bookstore;

public class HibernateUtil {

	private static SessionFactory sessionFactoryObj;

	public static SessionFactory getSessionFactory() {
		if (sessionFactoryObj == null) {
			sessionFactoryObj = buildSessionFactory();
		}
		return sessionFactoryObj;
	}

	// Create the hiberate's SessionFactory object
	private static SessionFactory buildSessionFactory() {
		// Create Configuration Instance & pass hibernate Configuration file
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		return configObj.addAnnotatedClass(Bookstore.class).addAnnotatedClass(Book.class)
				.buildSessionFactory(serviceRegistryObj);
	}
}
