package library;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class DatabaseUtility {

	private DataSource dataSource;
	private Connection connection = null;
	private PreparedStatement prepStatement;
	private ResultSet resultSet;
	
	public DatabaseUtility(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	//-----------------------  BOOKS -----------------------
	
	public List<Book> getBooks() throws SQLException {
		
		System.out.println("In the get Books");
		List<Book> books = new ArrayList<>();
		
		connection = dataSource.getConnection();
		System.out.println("after connection");

		String sqlStatement = "SELECT * from books";
		prepStatement = connection.prepareStatement(sqlStatement);
		resultSet = prepStatement.executeQuery();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			String date = resultSet.getString("published_date");
			int borrower = resultSet.getInt("borrower_id");
			Boolean isBorrowed = resultSet.getBoolean("isBorrowed");
			
			Book book = new Book(id, title, author, date, borrower, isBorrowed);
			books.add(book);
		}
		
		close(connection, prepStatement, resultSet);
		return books;
	}
	
	public List<Book> getBooksByFilter(String selectedFilter) throws SQLException {
		
		String sqlStatement = "SELECT * from books";
		
		switch(selectedFilter) {
		case "ID":
			sqlStatement = "SELECT * from books ORDER BY id";
			break;
		case "Title":
			sqlStatement = "SELECT * from books ORDER BY title";
			break;
		case "Author":
			sqlStatement = "SELECT * from books ORDER BY author";
			break;
		case "Published Date":
			sqlStatement = "SELECT * from books ORDER BY published_date";
			break;
		case "Available":
			sqlStatement = "SELECT * from books ORDER BY isBorrowed ASC";
			break;
		case "Borrowed":
			sqlStatement = "SELECT * from books ORDER BY isBorrowed DESC";
			break;
		}
	
		List<Book> filteredBooks = new ArrayList<>();
		
		connection = dataSource.getConnection();
		prepStatement = connection.prepareStatement(sqlStatement);
		resultSet = prepStatement.executeQuery();
		
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			String date = resultSet.getString("published_date");
			int borrower = resultSet.getInt("borrower_id");
			Boolean isBorrowed = resultSet.getBoolean("isBorrowed");
			
			Book book = new Book(id, title, author, date, borrower, isBorrowed);
			filteredBooks.add(book);
		}
		
		close(connection, prepStatement, resultSet);
		return filteredBooks;
	}

	public Book getBook(int id) throws SQLException {
		
		Book book = null;
		
		connection = dataSource.getConnection();
		String sqlStatement = "SELECT * from books WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setInt(1, id);
		resultSet = prepStatement.executeQuery();
		
		while(resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			String date = resultSet.getString("published_date");
			int borrower = resultSet.getInt("borrower_id");
			Boolean isBorrowed = resultSet.getBoolean("isBorrowed");
			
			book = new Book(id, title, author, date, borrower, isBorrowed);
		}
		
		close(connection, prepStatement, resultSet);
		return book;
	}
	
	public void addBook(Book book) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "INSERT INTO books (title, author, published_date, borrower_id, isBorrowed) VALUES (?, ?, ?, ?, ?)";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setString(1, book.getTitle());
		prepStatement.setString(2, book.getAuthor());
		prepStatement.setString(3, book.getPublishedDate());
		prepStatement.setInt(4, book.getBorrower_id());
		prepStatement.setBoolean(5, book.IsBorrowed());
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);

	}
	
	public void updateBook(Book book) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "UPDATE books SET title = ?, author = ?, published_date = ?, borrower_id = ?, isBorrowed = ? WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setString(1, book.getTitle());
		prepStatement.setString(2, book.getAuthor());
		prepStatement.setString(3, book.getPublishedDate());
		prepStatement.setInt(4, book.getBorrower_id());
		prepStatement.setBoolean(5, book.IsBorrowed());
		prepStatement.setInt(6, book.getId());
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	public void updateBookStatus(Book book) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "UPDATE books SET borrower_id = ?, isBorrowed = ? WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setInt(1, book.getBorrower_id());
		prepStatement.setBoolean(2, book.IsBorrowed());
		prepStatement.setInt(3, book.getId());
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	public void deleteBook(int id) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "DELETE from books WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setInt(1, id);
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	
	//----------------------- MEMBERS -----------------------
	
	public Boolean memberExists(int id) {
		
		Boolean exists = false;
		
		try {
			connection = dataSource.getConnection();
			
			String sqlStatement = "SELECT COUNT(id) FROM members WHERE id = ?";
			prepStatement = connection.prepareStatement(sqlStatement);
			prepStatement.setInt(1, id);
			resultSet = prepStatement.executeQuery();
			
			if (resultSet.next()) {
				if (resultSet.getInt(1) > 0) {
					exists = true;
				}				
			}
			
			close(connection, prepStatement, resultSet);
		} catch (SQLException e) {e.printStackTrace();}
		
		return exists;
	}
	
	
	public List<Member> getMembers() throws SQLException {
		
		List<Member> members = new ArrayList<>();
		
		connection = dataSource.getConnection();
		String sqlStatement = "SELECT * FROM members";
		prepStatement = connection.prepareStatement(sqlStatement);
		resultSet = prepStatement.executeQuery();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String telephone = resultSet.getString("telephone");
			String membership_date = resultSet.getString("membership_date");
			
			Member member = new Member(id, name, email, telephone, membership_date);
			members.add(member);
		}
		close(connection, prepStatement, resultSet);
		return members;
	}
	
	public Member getMember(int id) throws SQLException {
		
		Member member = null;
		
		connection = dataSource.getConnection();
		String sqlStatement = "SELECT * from members WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setInt(1, id);
		resultSet = prepStatement.executeQuery();
		
		while(resultSet.next()) {
			String name = resultSet.getString("name");
			String email = resultSet.getString("email");
			String tel = resultSet.getString("telephone");
			String date = resultSet.getString("membership_date");
			
			member = new Member(id, name, email, tel, date);
		}
		
		close(connection, prepStatement, resultSet);
		return member;
	}
	
	public void addMember(Member member) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "INSERT INTO members (name, email, telephone, membership_date) VALUES (?, ?, ?, ?)";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setString(1, member.getName());
		prepStatement.setString(2, member.getEmail());
		prepStatement.setString(3, member.getPhone());
		prepStatement.setString(4, member.getMembershipDate());
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	public void updateMember(Member member) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "UPDATE members SET email = ?, telephone = ? WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setString(1, member.getEmail());
		prepStatement.setString(2, member.getPhone());
		prepStatement.setInt(3, member.getId());
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	public void deleteMember(int id) throws SQLException {
		
		connection = dataSource.getConnection();
		String sqlStatement = "DELETE from members WHERE id = ?";
		prepStatement = connection.prepareStatement(sqlStatement);
		prepStatement.setInt(1, id);
		
		prepStatement.execute();
		close(connection, prepStatement, resultSet);
	}
	
	
	private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		
			if(statement != null) {
				statement.close();
			}
		
			if(connection != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
