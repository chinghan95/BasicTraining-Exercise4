package bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import bookstore.conn.BookDao;
import bookstore.conn.BookstoreDao;
import bookstore.conn.GenericsDao;
import bookstore.javabeans.Book;
import bookstore.javabeans.Bookstore;

@WebServlet(name = "BookServlet", urlPatterns = { "/BookServlet" })
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQ_PARAM_ACTION = "action";
	private static final String REQ_PARAM_STOREID = "storeId";
	private static final String REQ_PARAM_BOOKID = "bookId";
	private static final String REQ_PARAM_BOOKNAME = "bookName";
	private static final String REQ_PARAM_CATEGORY = "category";
	private static final String REQ_PARAM_PRICE = "price";

	private static GenericsDao<Book> genericsDao = new GenericsDao<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getParameter(REQ_PARAM_ACTION)) {
			case "create":
				createBook(request, response);
				break;
			case "update":
				updateBook(request, response);
				break;
			default:
				System.out.println("Can not map the action's name.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getParameter(REQ_PARAM_ACTION)) {
			case "edit":
				showEditForm(request, response);
				break;
			case "delete":
				deleteBook(request, response);
				break;
			default:
				listBook(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookstoreId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		List<Book> listBook = genericsDao.getByFiledName(Book.class, "storeId", bookstoreId);
		request.setAttribute("booksData", listBook);
		request.setAttribute("bookstore", genericsDao.findById(bookstoreId, Bookstore.class));
		request.getRequestDispatcher("book/list.jsp").forward(request, response);
	}

	private void createBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int storeId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		String bookName = request.getParameter(REQ_PARAM_BOOKNAME);
		String category = request.getParameter(REQ_PARAM_CATEGORY);
		float price = Float.parseFloat(request.getParameter(REQ_PARAM_PRICE));

		Bookstore bookstore = BookstoreDao.getBookstoreById(storeId);
		Book book = new Book(bookName, category, price, bookstore);
		
		// Save the data
		genericsDao.save(book);
//		BookDao.save(book);
		response.sendRedirect("BookServlet?action=list&storeId=" + storeId);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int storeId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		int bookId = Integer.parseInt(request.getParameter(REQ_PARAM_BOOKID));
		Book book = genericsDao.findById(bookId, Book.class);
//		Book book = BookDao.getBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("book/update.jsp?storeId=" + storeId).forward(request, response);
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int storeId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		int bookId = Integer.parseInt(request.getParameter(REQ_PARAM_BOOKID));
		String bookName = request.getParameter(REQ_PARAM_BOOKNAME);
		String category = request.getParameter(REQ_PARAM_CATEGORY);
		float price = Float.parseFloat(request.getParameter(REQ_PARAM_PRICE));
		Bookstore bookstoreById = BookstoreDao.getBookstoreById(storeId);
		Book book = new Book(bookId, bookName, category, price, bookstoreById);
		
		// Update the data
		genericsDao.update(book);
//		BookDao.updateBook(book);
		response.sendRedirect("BookServlet?action=list&storeId=" + storeId);
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int storeId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		int bookId = Integer.parseInt(request.getParameter(REQ_PARAM_BOOKID));

		// Delete data
		genericsDao.delete(bookId, Book.class);
//		BookDao.deleteBook(bookId);
		response.sendRedirect("BookServlet?action=list&storeId=" + storeId);
	}
}
