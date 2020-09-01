<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
</head>
<body>
	<h1>Update Book Form</h1>
	
	<%
	String storeId=request.getParameter("storeId");
	pageContext.setAttribute("storeId", storeId);
	%>
	
	<form action="BookServlet?action=update&storeId=<c:out value='${storeId}'/>" method="post">
		<input type="hidden" name="bookId" value="<c:out value='${book.getBookId()}'/>" />
		
		<label for="bookName">Book Name:</label><br>
		<input type="text" name="bookName" value="<c:out value='${book.getBookName()}'/>" /><br>
		<label for="category">Category:</label><br>
		<input type="text" name="category" value="<c:out value='${book.getCategory()}'/>" /><br>
		<label for="price">Price:</label><br>
		<input type="text" name="price" value="<c:out value='${book.getPrice()}'/>" /><br><br>
		
		<input type="submit" value="Update & Save">
	</form>
</body>
</html>