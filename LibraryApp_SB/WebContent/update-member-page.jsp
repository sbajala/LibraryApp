<%@page import="library.Member" %>
<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<body>

	<br/><br/><br/>
<div class="flex-container">
	<div class="biggerBox">
		
		<h1 class="title">UPDATE MEMBER</h1>

		<div class="flex-container">
			<div class="box">
				<form action="MemberServlet" method="POST">
					<input type="hidden" name="action" value="UPDATE_MEMBER"/> <br/> <br/>
					<input type="hidden" name="member_id" value="${member.id}">
					<input type="hidden" name="name" value="${member.name}">
					<b>Name:</b> ${member.name} <br/> <br/>
					<b>Email:</b> <input type="text" name="email" value="${member.email}"/> <br/> <br/>
					<b>Phone:</b> <input type="text" name="phone" value="${member.phone}"/> <br/> <br/>
					<b>Date:</b> ${member.membershipDate}<br/> <br/> <br/>
					<input type="hidden" name="membershipDate" value="${member.membershipDate}">
					<input style="text-align: center" type="submit" value="UPDATE MEMBER" class="button"/> <br/>
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
			<br/><br/><br/>
		</form>
	</div>
	
	<br/><br/>
	<div class="flex-container">
		<form action="LibraryServlet" method="POST">
			<input type="hidden" name="action" value="LIST_BOOKS"/>
			<input type="submit" value="RETURN TO LIBRARY" class="button"/>
			<br/><br/><br/>
		</form>
	</div>
	
</body>
</html>