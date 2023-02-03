<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Sharmaine's Library</title>
<% String message = (String)request.getAttribute("message"); %>
</head>
<link type="text/css" rel="stylesheet" href="css/finalstyle.css">
<body>
	<br/><br/>
	<h1 class="title">MEMBERS</h1> <br/>
	
	
	<div class="flex-container">
		<div class="biggerBox" style="width: 1000px;">
	
		<h3 style="text-align:center"><%= message %></h3>
	
			<table>
				<tr>
					<th> ID </th>
					<th> NAME </th>
					<th> EMAIL </th>
					<th> TELEPHONE </th>
					<th> MEMBER SINCE </th>
					<th> OPTIONS </th>
				</tr>
			
		
				<c:forEach var="member" items="${members_list}">
		
					<c:url var="updateLink" value="MemberServlet">
						<c:param name="action" value="LIST_MEMBER"/>
						<c:param name="member_id" value="${member.id}"/>
					</c:url>
			
					<c:url var="deleteLink" value="MemberServlet">
						<c:param name="action" value="DELETE_MEMBER"/>
						<c:param name="member_id" value="${member.id}"/>
					</c:url>	
		
				<tr>
					<td class="id"> ${member.id} </td>
					<td> ${member.name} </td>
					<td> ${member.email} </td>
					<td> ${member.phone} </td>
					<td> ${member.membershipDate} </td>
					<td> <a href="${updateLink}">Edit</a> - <a href="${deleteLink}">Delete</a> </td>
				</tr>
				</c:forEach>
			</table>
			<br/><br/><br/>
		</div>
	</div>
	
	<br/><br/>
	<div class="flex-container">
		<a href="add-member-page.jsp" class="button">ADD MEMBER</a>
	</div>
	
	<br/>
	<div class="flex-container">
		<a href="LibraryServlet" class="button">RETURN</a>
	</div>
	<br/><br/>
</body>
</html>