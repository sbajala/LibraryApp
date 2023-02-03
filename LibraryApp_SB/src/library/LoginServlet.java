package library;


import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
  
	  @Override
	   public void init() throws ServletException {
			super.init();
	   }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (isLoginValid(username, password)) {
			response.sendRedirect("http://localhost:8080/FinalProject/LibraryServlet");
		} else {
			request.setAttribute("message", "Incorrect login information. Try again.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//NOT IMPLEMENTING ANY SECURITY, JUST CREATING LOGIN FOR ADMIN PURPOSES :-)
	private Boolean isLoginValid(String username, String password) {
		return (username.equals("admin") && password.equals("admin"));
	}

}
