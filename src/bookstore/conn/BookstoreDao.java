package bookstore.conn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bookstore.javabeans.Bookstore;

public class BookstoreDao {

	public static void save(Bookstore bookstore) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			// Get Session object from SessionFactory
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			// Get Transaction object from Session object and start transaction
			transaction = sessionObj.beginTransaction();
			// Save a bookstore object
			sessionObj.save(bookstore);
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

	@SuppressWarnings("unchecked")
	public static List<Bookstore> getAllBookstores() {
		// List to hold Bookstore objects
		List<Bookstore> listBookstore = new ArrayList<Bookstore>();
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			listBookstore = sessionObj.createQuery("FROM Bookstore").getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return listBookstore;
	}

	public static Bookstore getBookstoreById(int id) {
		Bookstore bookstore = null;
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Get a bookstore object
			bookstore = sessionObj.get(Bookstore.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return bookstore;
	}

	public static void updateBookstore(Bookstore bookstore) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Update a bookstore object
			sessionObj.update(bookstore);
			System.out.println("bookstore is updated");
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

	public static void deleteBookstore(int bookstoreId) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Delete a bookstore object
			Bookstore bookstore = getBookstoreById(bookstoreId);
			if (bookstore != null) {
				sessionObj.delete(bookstore);
				System.out.println("bookstore is deleted");
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
