package bookstore.conn;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import bookstore.javabeans.Book;

public class BookDao {

	public static void save(Book book) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			// Get Session object from SessionFactory
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			// Get Transaction object from Session object and start transaction
			transaction = sessionObj.beginTransaction();
			// Save a bookstore object
			sessionObj.save(book);
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
	public static List<Book> getAllBooks(int bookstoreId) {
		// List to hold Book objects
		List<Book> listBook = new ArrayList<Book>();
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query qrFetch = sessionObj.createQuery("FROM Book WHERE Store_Id=:storeId");
			qrFetch.setParameter("storeId", bookstoreId);
			listBook = qrFetch.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return listBook;
	}

	public static Book getBookById(int bookId) {
		Book book = null;
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Get a book object
			book = sessionObj.get(Book.class, bookId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			if (sessionObj != null)
				sessionObj.close();
		}
		return book;
	}

	public static void updateBook(Book book) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Update a book object
			sessionObj.update(book);
			System.out.println("book is updated");
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
	
	public static void deleteBook(int bookId) {
		Session sessionObj = null;
		Transaction transaction = null;
		try {
			sessionObj = HibernateUtil.getSessionFactory().openSession();
			transaction = sessionObj.beginTransaction();
			// Delete a book object
			Book book = getBookById(bookId);
			if (book != null) {
				sessionObj.delete(book);
				System.out.println("book is deleted");
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
