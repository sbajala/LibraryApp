<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<body>

	<br/><br/><br/>
	
	<div class="flex-container">
		<div class="biggerBox">
			
			<h1 class="title">UPDATE BOOK</h1>
				
				<div class="flex-container">
					<div class="box">
						<form action="UpdateServlet" method="POST">
							<input type="hidden" name="action" value="UPDATE_BOOK"/>
							<input type="hidden" name="book_id" value="${book.id}"/>
							<b>Book ID:</b> ${book.id} <br/> <br/>
							<b>Title:</b> <input type="text" name="title" value="${book.title}"/> <br/> <br/>
							<b>Author:</b> <input type="text" name="author" value="${book.author}"/> <br/> <br/>
							<b>Published Date:</b> <input type="date" name="published_date" value="${book.publishedDate}"/> <br/> <br/>
							<input style="text-align: center" type="submit" value="Save" class="button"/> <br/>
						</form>
					</div>
				</div>
			<br/><br/><br/>
		</div>
	</div>
	
	<br/><br/>
	<div class="flex-container">
		<form action="UpdateServlet" method="POST">
			<input type="hidden" name="action" value="REMOVE_BOOK"/>
			<input type="hidden" name="book_id" value="${book.id}"/>
			<input type="submit" value="Delete Book" onclick="return confirm('Are you sure you want to delete book?')" class="delete"/>
		</form>
	</div>
	
	<br/><br/>
	<div class="flex-container">
		<form action="LibraryServlet" method="POST">
			<input type="hidden" name="action" value="LIST_BOOKS"/>
			<input type="submit" value="Return" class="button"/>
		</form>
	</div>
	
</body>
</html>