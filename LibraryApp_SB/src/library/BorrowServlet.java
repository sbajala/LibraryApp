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

@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	
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
		request.setAttribute("services", services);
		
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
		case "BORROW_BOOK":
			borrowBook(request, response);	
			break;
		}
	}
	
	private void listBook(HttpServletRequest request, HttpServletResponse response) {
		String ID = request.getParameter("book_id");
		Book BOOK = services.getBook(Integer.parseInt(ID));
		request.setAttribute("book", BOOK);
		dispatcher = request.getRequestDispatcher("/borrow-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void borrowBook(HttpServletRequest request, HttpServletResponse response) {
		
		int borrower_id = Integer.parseInt(request.getParameter("borrower_id"));
		
		int id = Integer.parseInt(request.getParameter("book_id"));
		Book borrow_book = services.getBook(id);
		
		if (services.memberExists(borrower_id)) {
			if (!borrow_book.IsBorrowed()) {
				borrow_book.setIsBorrowed(true);
				borrow_book.setBorrower_id(borrower_id);
				services.setMessage("Book has been borrowed.");
				services.updateBookStatus(borrow_book);
			} else {
				services.setMessage("Book is already borrowed.");
			}
		}
		
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
