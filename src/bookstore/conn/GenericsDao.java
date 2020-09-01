package bookstore.conn;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericsDao<T> {

	public void save(T t) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			// Get Session object from SessionFactory
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			// Get Transaction object from Session object and start transaction
			transaction = sessionObj.beginTransaction();
			// Save a bookstore object
			sessionObj.save(t);
			// Commit the transactions to the database
			transaction.commit();
		} catch (Exception e) {
			// ROLLBACK TRANSACTION to erase all data modifications made from
			// the start of the transaction or to a savepoint.
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
	}

	public List<T> findAll(Class<T> persistClass) {
		// List to hold objects
		List<T> t = new ArrayList<T>();
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Get Criteria Builder
			CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
			// Create Criteria
			CriteriaQuery<T> query = builder.createQuery(persistClass);
			Root<T> root = query.from(persistClass);
			query.select(root);
			// Use criteria to query with session to fetch all contacts
			t = sessionObj.createQuery(query).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			
			if (sessionObj != null)
				sessionObj.close();
		}
		return t;
	}

	public List<T> getByFiledName(Class<T> persistClass, String fieldName, Object value) {
		// List to hold objects
		List<T> t = new ArrayList<T>();
		Session sessionObj = null;
		Transaction transaction = null;

		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			CriteriaBuilder builder = sessionObj.getCriteriaBuilder();
			CriteriaQuery<T> query = builder.createQuery(persistClass);
			Root<T> root = query.from(persistClass);
			query.select(root).where(builder.equal(root.get(fieldName), value));

			// Use criteria to query with session to fetch all contacts
			t = sessionObj.createQuery(query).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	public T findById(int id, Class<?> entityClass) {
		T t = null;
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Get object
			t = (T) sessionObj.get(entityClass, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return t;
	}

	public void update(T t) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Update object
			sessionObj.update(t);
			System.out.println("Updated");
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
	}

	public void delete(int id, Class<T> entityClass) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Delete object
			T t = sessionObj.byId(entityClass).load(id);
//			T t = findById(id, entityClass);
			if (t != null) {
				sessionObj.delete(t);
				System.out.println("Deleted");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}

	}

}
