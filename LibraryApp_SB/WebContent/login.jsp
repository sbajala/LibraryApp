<html>
<head>
<title>Login</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>
<% String message = (String)request.getAttribute("message");
	if (message == null) {
		message = "Login";
	}
%>
<body>
	
	<br/><br/>
	<h1 class="title">Sharmaine's Library</h1>
	<br/><br/>
	
	<div class="flex-container">
		<div class="biggerBox">
			
			<h3 style="text-align: center"><%= message %></h3>
				
				<div class="flex-container">
					<div class="box">
						<form action="LoginServlet" method="POST">
							<input type="hidden" name="action" value="LOGIN"/> 
							<b>Username</b> <br/> 
							<input type="text" name="username"/> <br/> <br/>
							<b>Password</b> <br/>
							<input type="password" name="password"/> <br/><br/> <br/>
				<input style="text-align: center" type="submit" value="LOGIN" class="button"/>
						</form>
					</div>
				</div>
			<br/><br/>
			
		</div>
	</div>
		
		<br/><br/>
		<div class="flex-container">
			<br/>
		</div>
		
</body>
</html>