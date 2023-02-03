package library;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	private Services services;
	private RequestDispatcher dispatcher;
	
	@Resource(name="jdbc/library")
	private DataSource dataSource;
	
   @Override
   public void init() throws ServletException {
		super.init();
		services = new Services(dataSource);
   }
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action == null) {
			action = "LIST_BOOK";
		}
		setAction(action, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void setAction(String action, HttpServletRequest request, HttpServletResponse response) {
		switch(action) {
		case "LIST_BOOK":
			listBook(request, response);
			break;
		case "UPDATE_BOOK":
			updateBook(request, response);	
			break;
		case "REMOVE_BOOK":
			deleteBook(request, response);
			break;
		}
	}
	
	private void listBook(HttpServletRequest request, HttpServletResponse response) {
		
		String ID = request.getParameter("book_id");
		Book BOOK = services.getBook(Integer.parseInt(ID));
		request.setAttribute("book", BOOK);
		dispatcher = request.getRequestDispatcher("update-book-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateBook(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("book_id");
		String update_title = request.getParameter("title");
		String update_author = request.getParameter("author");
		String pub_date = request.getParameter("published_date");
		
		Book book = services.getBook(Integer.parseInt(id));
		int borrower_id = book.getBorrower_id();
		Boolean isBorrowed = book.IsBorrowed();
		
		Book updatedBook = new Book(Integer.parseInt(id), update_title, update_author, pub_date, borrower_id, isBorrowed);
		services.updateBook(updatedBook);
		dispatchList(request, response);
		
	}
	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
		
		String remove_id = request.getParameter("book_id");
		services.deleteBook(Integer.parseInt(remove_id));
		dispatchList(request, response);
	}
	
	private void dispatchList(HttpServletRequest request, HttpServletResponse response) {
		
		List<Book> books = services.getBooks();
		request.setAttribute("books_list", books);
		request.setAttribute("message", services.getMessage());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/library-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	

}
