package library;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;


@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	
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
		case "RETURN_BOOK":
			returnBook(request, response);	
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
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response) {

		String book_ID = request.getParameter("book_id");
		Book return_book = services.getBook(Integer.parseInt(book_ID));
		int borrowerID = Integer.parseInt(request.getParameter("borrower"));
		
		if (services.memberExists(borrowerID)) {
			
			if (return_book.IsBorrowed()) {
				
				if (borrowerID == return_book.getBorrower_id()) {				
					return_book.setIsBorrowed(false);
					return_book.setBorrower_id(0);
					services.setMessage("Book has been returned.");
					services.updateBookStatus(return_book);
				} else {
					services.setMessage("Cannot return book. Not the same borrower.");
				}			
			} else {
				services.setMessage("Cannot return book that is not borrowed.");
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
