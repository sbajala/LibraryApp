<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<body>

	<br/><br/><br/>
	
	<div class="flex-container">
		<div class="biggerBox">
			
			<h1 class="title">BOOK DETAILS</h1>
	
			<div class=flex-container>
				<div class="box">
					<b>Book ID:</b> ${book.id} <br/>
					<b>Title:</b> ${book.title} <br/>
					<b>Author:</b> ${book.author} <br/>
					<b>Published date:</b> ${book.publishedDate} <br/>
				</div>
			</div>
	
			<br/><br/>
			<h1 class="title"> RETURN BOOK </h1>
	
			<div class="flex-container">
				<div class="box"> <br/>
					<form action="ReturnServlet" method="POST">
						<b>Borrower's ID:</b> <input type="text" name="borrower"/>
						<input type="hidden" name="action" value="RETURN_BOOK"/>
						<input type="hidden" name="book_id" value="${book.id}"/> <br/><br/> <br/>
						<input type="submit" value="RETURN BOOK" class="button"/>
					</form>
				</div>
			</div>
		<br/><br/><br/>
		</div>
	</div>
	
	<br/><br/><br/>
		<div class="flex-container">
		<form action="LibraryServlet" method="POST">
			<input type="hidden" name="action" value="LIST_BOOKS"/>
			<input type="submit" value="Return" class="button"/>
		</form>
	</div>
	
</body>
</html>