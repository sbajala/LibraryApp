package library;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String publishedDate;
	private int borrower_id;
	private Boolean isBorrowed;
	private String status;
	
	
	public Book(int id, String title, String author, String publishedDate, int borrower_id, Boolean isBorrowed) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
		this.borrower_id = borrower_id;
		this.isBorrowed = isBorrowed;
	}
	
	public Book(String title, String author, String publishedDate, int borrower_id, Boolean isBorrowed) {
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
		this.borrower_id = borrower_id;
		this.isBorrowed = isBorrowed;
	}
	
	public Book(String title, String author, String publishedDate) {
		this.title = title;
		this.author = author;
		this.publishedDate = publishedDate;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishedDate() {
		return publishedDate;
	}


	public void setPublishedDate(String date) {
		this.publishedDate = date;
	}

	public int getBorrower_id() {
		//TODO
		return borrower_id;
	}

	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}

	public Boolean IsBorrowed() {
		return isBorrowed;
	}

	public void setIsBorrowed(Boolean isBorrowed) {
		this.isBorrowed = isBorrowed;
	}

	public String getStatus() {
		if(isBorrowed) {
			status = "Borrowed";
		} else {
			status = "Available";
		}
		return status;
	}

}
