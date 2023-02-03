<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<body>

	<br/><br/><br/>
<div class="flex-container">
	<div class="biggerBox">
		
		<h1 class="title">ADD MEMBER</h1>

		<div class="flex-container">
			<div class="box">
				<form action="MemberServlet" method="POST" style="padding: 10px">
					<input type="hidden" name="action" value="ADD_MEMBER"/> <br/> <br/>
					<b>Name:</b> <input type="text" name="name"/> <br/> <br/>
					<b>Email:</b> <input type="text" name="email"/> <br/> <br/>
					<b>Phone:</b> <input type="text" name="phone"/> <br/> <br/>
					<b>Date:</b> <input type="date" name="membership_date"/> <br/> <br/> <br/>
					<input style="text-align: center" type="submit" value="ADD MEMBER" class="button"/> <br/>
				</form>
			</div>
		</div>
		<br/><br/></br>
	</div>
</div>
	
	<br/><br/>
	<div class="flex-container">
		<form action="MemberServlet" method="POST">
			<input type="hidden" name="action" value="VIEW_MEMBERS"/>
			<input type="submit" value="RETURN TO MEMBERS" class="button"/>
		</form>
	</div>
	
	<br/>
	<div class="flex-container">
		<form action="LibraryServlet" method="POST">
			<input type="hidden" name="action" value="LIST_BOOKS"/>
			<input type="submit" value="RETURN TO LIBRARY" class="button"/>
			<br/><br/><br/>
		</form>
	</div>
	
</body>
</html>