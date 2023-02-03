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


@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	
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
		case "LIST_BOOKS":
			dispatchList(request, response);
			break;

		case "FILTER_BOOKS":
			
			String filter = request.getParameter("filter");
			if (filter == null) {
				filter = "ID";
			}
			List<Book> filteredBooks = services.getBooksByFilter(filter);
			request.setAttribute("books_list", filteredBooks);
			request.setAttribute("message", services.getMessage());
			dispatcher = request.getRequestDispatcher("/library-page.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
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
