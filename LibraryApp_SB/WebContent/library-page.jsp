<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="library.Services"%>
<html>
<head>
<title>Sharmaine's Library</title>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
</head>

<% String message = (String)request.getAttribute("message"); %>

<body>
	<br/><br/>
	<h1 class="title">SHARMAINE'S LIBRARY</h1> <br/>
	
	<div class="flex-container">
		<div class="biggerBox" style="width: 1050px">
		
		<br/> <h3 style="text-align: center">Message: <%= message %></h3> <br/>
			
			<div class="flex-container" style="align-items: right">
				<form action="LibraryServlet" method="POST">
					<input type="hidden" name="action" value="FILTER_BOOKS"/>
					<select name="filter" style="padding: 5px">
						<option selected disabled hidden>Filter By</option>
						<option>ID</option>
						<option>Title</option>
						<option>Author</option>
						<option>Published Date</option>
						<option>Available</option>
						<option>Borrowed</option>
					</select>
					<input type="submit" value="DISPLAY" class="button"/> <br/>
				</form>
			</div>
	
			<table>
				<tr>
					<th> ID </th>
					<th> TITLE </th>
					<th> AUTHOR </th>
					<th> PUBLISHED DATE </th>
					<th> STATUS </th>
					<th> BORROWER ID </th>
					<th> OPTIONS </th>
				</tr>
			
		
				<c:forEach var="book" items="${books_list}">
		
					<c:url var="updateLink" value="UpdateServlet">
						<c:param name="action" value="LIST_BOOK"/>
						<c:param name="book_id" value="${book.id}"/>
					</c:url>
		
					<c:url var="borrowLink" value="BorrowServlet">
						<c:param name="action" value="LIST_BOOK"/>
						<c:param name="book_id" value="${book.id}"/>
					</c:url>
			
					<c:url var="returnLink" value="ReturnServlet">
						<c:param name="action" value="LIST_BOOK"/>
						<c:param name="book_id" value="${book.id}"/>
					</c:url>	
		
				<tr>
					<td class="id"> ${book.id} </td>
					<td> ${book.title} </td>
					<td> ${book.author} </td>
					<td class="center"> ${book.publishedDate} </td>
					<td class="center"> ${book.status} </td>
					<td class="center"> ${book.borrower_id} </td>
					<td class="center"> <a href="${updateLink}">Edit</a> - <a href="${borrowLink}">Borrow</a> - <a href="${returnLink}">Return</a> </td>
				</tr>
				</c:forEach>
			</table>
			<br/><br/><br/>
		</div>
	</div>
	
	<br/><br/> <br/>
	<div class="flex-container">
		<a href="add-book-page.jsp" class="button">ADD BOOK</a>
	</div>

	<br/>
	
	<div class="flex-container">
	<form action="MemberServlet" action="POST">
		<input type="hidden" name="action" value="VIEW_MEMBERS"/>
		<input type="submit" value="VIEW MEMBERS" class="button"/>
	</form>
	</div>
	
	<div class="flex-container">
		<a href="login.jsp" class="button">LOG OUT</a>
	</div>
	<br/><br/>
	
</body>
</html>