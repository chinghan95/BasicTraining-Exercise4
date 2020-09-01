package bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import bookstore.conn.BookstoreDao;
import bookstore.conn.GenericsDao;
//import bookstore.javabeans.Book;
import bookstore.javabeans.Bookstore;

@WebServlet(name = "BookstoreServlet", urlPatterns = { "/BookstoreServlet" })
public class BookstoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String REQ_PARAM_ACTION = "action";
	private static final String REQ_PARAM_STOREID = "storeId";
	private static final String REQ_PARAM_STORENAME = "storeName";
	private static final String REQ_PARAM_STOREADDRESS = "storeAddress";
	private static final String REQ_PARAM_STORETELEPHONE = "storeTelephone";

	private static GenericsDao<Bookstore> genericsDao = new GenericsDao<>();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			switch (request.getParameter(REQ_PARAM_ACTION)) {
			case "create":
				createBookstore(request, response);
				break;
			case "update":
				updateBookstore(request, response);
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
			String action = request.getParameter(REQ_PARAM_ACTION) == null ? "list"
					: request.getParameter(REQ_PARAM_ACTION);
			switch (action) {
			case "edit":
				showEditForm(request, response);
				break;
			case "delete":
				deleteBookstore(request, response);
				break;
			default:
				listBookstore(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listBookstore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bookstore> listBookstore = genericsDao.findAll(Bookstore.class);
		request.setAttribute("bookstoresData", listBookstore);
		request.getRequestDispatcher("bookstore/list.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookstoreId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		Bookstore bookstore = genericsDao.findById(bookstoreId, Bookstore.class);
		request.setAttribute("bookstoreById", bookstore);
		request.getRequestDispatcher("bookstore/update.jsp").forward(request, response);
	}

	private void createBookstore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String storeName = request.getParameter(REQ_PARAM_STORENAME);
		String storeAddress = request.getParameter(REQ_PARAM_STOREADDRESS);
		String storeTelephone = request.getParameter(REQ_PARAM_STORETELEPHONE);

		Bookstore bookstore = new Bookstore(storeName, storeAddress, storeTelephone);

		// Save the data
		genericsDao.save(bookstore);
//		BookstoreDao.save(bookstore);
		response.sendRedirect("BookstoreServlet?action=list");
	}

	private void updateBookstore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookstoreId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		String storeName = request.getParameter(REQ_PARAM_STORENAME);
		String storeAddress = request.getParameter(REQ_PARAM_STOREADDRESS);
		String storeTelephone = request.getParameter(REQ_PARAM_STORETELEPHONE);
		Bookstore bookstore = new Bookstore(bookstoreId, storeName, storeAddress, storeTelephone);
		
		// Update the data
		genericsDao.update(bookstore);
//		BookstoreDao.updateBookstore(bookstore);
		response.sendRedirect("BookstoreServlet?action=list");
	}

	private void deleteBookstore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int bookstoreId = Integer.parseInt(request.getParameter(REQ_PARAM_STOREID));
		
		// Delete data
		genericsDao.delete(bookstoreId, Bookstore.class);
//		BookstoreDao.deleteBookstore(bookstoreId);
		response.sendRedirect("BookstoreServlet?action=list");
	}

}
