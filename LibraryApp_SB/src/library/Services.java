package library;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

//ANOTHER LAYER TO VALIDATE AND SET DESCRIPTIVE MESSAGES FOR ADMIN

public class Services {
	
	private DatabaseUtility dbUtil;
	private String message = "Welcome!";
	
	public Services(DataSource dataSource) {
		dbUtil = new DatabaseUtility(dataSource);
	}
	
	//-----------------------  BOOKS -----------------------
	
	public List<Book> getBooks() {
		try {
			return dbUtil.getBooks();
		} catch (SQLException e) {
			setMessage("An error occured. Could not retrieve books.");
		}
		return null;
	}
	
	public List<Book> getBooksByFilter(String filter) {
		try {
			return dbUtil.getBooksByFilter(filter);
		} catch (SQLException e) {
			setMessage("An error occured. Coult not filter books.");
		}
		return null;
	}
	
	public Book getBook(int id) {
		try {
				return dbUtil.getBook(id);
		} catch (SQLException e) {
			setMessage("Book ID does not exist.");
		}
		return null;			
	}
	
	public void addBook(Book book) {
		if (book !=null) {
			try {
				dbUtil.addBook(book);
				setMessage("Successfully added book.");
			} catch (SQLException e) {
				setMessage("An error occured. Could not add book.");
			}		
		}
	}
	
	public void updateBook(Book book) {
		try {
			dbUtil.updateBook(book);	
			setMessage("Successfully updated book.");
		} catch (SQLException e) {
			setMessage("An error occured. Could not update book.");
		}
	}
	
	public void updateBookStatus(Book book) {
		try {
			dbUtil.updateBookStatus(book);
			setMessage("Successfully updated book.");
		} catch (SQLException e) {
			setMessage("An error occured. Could not update book's status.");
		}
	}
	
	public void deleteBook(int id) {		
		try {
				dbUtil.deleteBook(id);
				setMessage("Successfully deleted book.");				
		} catch (SQLException e) {
			setMessage("An error occured. Could not delete book.");		
		}
		
	}

	//----------------------- MEMBERS -----------------------
	
	public Boolean memberExists(int id) {
		Boolean exists = false;
		if (dbUtil.memberExists(id)) {
			exists = true;
		} else {
			setMessage("Member ID does not exist.");
		}
		return exists;
	}
	
	
	public List<Member> getMembers() {
		try {
			return dbUtil.getMembers();
		} catch (SQLException e) {
			setMessage("An error occured. Could not retrieve members.");
		}
		return null;			
	}
	
	public Member getMember(int id) {
		try {
			if (dbUtil.memberExists(id)) {
				return dbUtil.getMember(id);					
			} else {
				setMessage("Member ID does not exist.");
			}
		} catch (SQLException e) {
			setMessage("An error occured. Could not retrieve member.");
		}
		return null;			
	}
	
	public void addMember(Member member) {
		if (member !=null) {
			try {
					dbUtil.addMember(member);
					setMessage("Successfully added member.");
			} catch (SQLException e) {
				setMessage("An error occured. Could not add member.");
			}		
		}
	}
	
	public void updateMember(Member member) {
		try {
				dbUtil.updateMember(member);	
				setMessage("Successfully updated member.");
		} catch (SQLException e) {
			setMessage("An error occured. Could not update member.");
		}
	}
	
	public void deleteMember(int id) {		
		try {
				dbUtil.deleteMember(id);
				setMessage("Successfully deleted member.");
		} catch (SQLException e) {
			setMessage("An error occured. Could not delete member.");		
		}
		
	}
	
	
	//----------------------- MESSAGES -----------------------
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
