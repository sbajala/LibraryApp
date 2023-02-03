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


@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	
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
			action = "VIEW_MEMBERS";
		}
		setAction(action, request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	private void setAction(String action, HttpServletRequest request, HttpServletResponse response) {
		switch(action) {
		case "VIEW_MEMBERS":			
			dispatchList(request, response);
			break;
			
		case "LIST_MEMBER":
			System.out.println("In LIST MEMBER");
			String id = request.getParameter("member_id");
			Member member = services.getMember(Integer.parseInt(id));
			request.setAttribute("member", member);
			dispatcher = request.getRequestDispatcher("update-member-page.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Breaking out of list members");
			break;
			
		case "ADD_MEMBER":
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String date = request.getParameter("membership_date");
			
			Member newMember = new Member(name, email, phone, date);
			services.addMember(newMember);
			dispatchList(request, response);
			break;
			
		case "UPDATE_MEMBER":
			String ID = request.getParameter("member_id");
			String NAME = request.getParameter("name");
			String EMAIL = request.getParameter("email");
			String PHONE = request.getParameter("phone");
			String MEMBERSHIPDATE = request.getParameter("membershipDate");
			
			Member updateMember = new Member(Integer.parseInt(ID), NAME, EMAIL, PHONE, MEMBERSHIPDATE);
			services.updateMember(updateMember);
			dispatchList(request, response);
			break;
			
		case "DELETE_MEMBER":
			String delete_ID = request.getParameter("member_id");
			services.deleteMember(Integer.parseInt(delete_ID));
			dispatchList(request, response);
			break;
		}
	}
	
	private void dispatchList(HttpServletRequest request, HttpServletResponse response) {
		List<Member> members = services.getMembers();
		request.setAttribute("members_list", members);
		request.setAttribute("message", services.getMessage());
		dispatcher = request.getRequestDispatcher("/members-page.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
