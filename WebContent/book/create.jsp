<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create book form</title>
</head>
<body> 

	<h1>Create a New Book</h1>
	
	<%
	String storeId=request.getParameter("storeId");
	pageContext.setAttribute("storeId", storeId);
	%>
	
	<form name="createBookForm" method="post" action="../BookServlet?action=create&storeId=<c:out value='${storeId}'/>">
	
		<label for="bookName">Book Name:</label><br> 
		<input type="text" name="bookName"><br> 
		<label for="category">Category:</label><br>
		<input type="text" name="category"><br> 
		<label for="price">Price:</label><br> 
		<input type="text" name="price"><br> <br> 
		<input type="submit" value="Save Book">
	</form>
	
<%-- 	<a href="BookServlet?action=create&storeId=<c:out value='${storeId}'/>"><button --%>
<!-- 								type="button" class="create">Save book</button></a> -->

</body>
</html>