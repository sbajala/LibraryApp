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


@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	
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
			action = "LIST_BOOKS";
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
		case "ADD_BOOK":
			addBook(request, response);	
			break;
		}
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("book_id");
		Book BOOK = services.getBook(Integer.parseInt(ID));
		request.setAttribute("book", BOOK);
		dispatcher = request.getRequestDispatcher("/return-book-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addBook(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String date = request.getParameter("published_date");
		String borrower = request.getParameter("borrower_id");
		String isBorrowed = request.getParameter("isBorrowed");
		 
		Book book = new Book(title, author, date, Integer.parseInt(borrower), Boolean.valueOf(isBorrowed));
		services.addBook(book);
		
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
		};
	}
	
}
