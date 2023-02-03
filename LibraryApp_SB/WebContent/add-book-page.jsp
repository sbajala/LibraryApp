<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<body>

	<br/><br/><br/>
<div class="flex-container">
	<div class="biggerBox">
		
		<h1 class="title">ADD BOOK</h1>

		<div class="flex-container">
			<div class="box">
				<form action="AddServlet" method="POST">
					<input type="hidden" name="action" value="ADD_BOOK"/> <br/> <br/>
					<b>Title:</b> <input type="text" name="title"/> <br/> <br/>
					<b>Author:</b> <input type="text" name="author"/> <br/> <br/>
					<b>Published Date:</b> <input type="date" name="published_date"/> <br/> <br/>
					<input type="hidden" name="borrower_id" value="0"/>
					<input type="hidden" name="isBorrowed" value="0"/>
					<input style="text-align: center" type="submit" value="ADD BOOK" class="button"/> <br/>
				</form>
			</div>
		</div>
		<br/><br/></br>
	</div>
</div>
	
	<br/><br/>
	<div class="flex-container">
		<form action="LibraryServlet" method="POST">
			<input type="hidden" name="action" value="LIST_BOOKS"/>
			<input type="submit" value="RETURN" class="button"/>
			<br/><br/><br/>
		</form>
	</div>
	
</body>
</html>