<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bookstore.javabeans.Bookstore"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookstore Application</title>
<style>
<%@ include file="/css/listStyle.css" %>
<%@ include file="/css/buttonStyle.css" %>
</style>
</head>
<body>

	<h1>Books Management</h1>
	<h3>
		<c:out value='${bookstore.getName()}' />
	</h3>
	
	<a href="book/create.jsp?storeId=<c:out value='${bookstore.getStoreId()}'/>"><button
								type="button" class="create">Create</button></a>
	<br><br>
	
	<table class="book_list_table">
		<thead>
			<tr>
				<th><b>Book Name</b></th>
				<th><b>Category</b></th>
				<th><b>Price</b></th>
				<th><b>Update</b></th>
				<th><b>Delete</b></th>
			</tr>
		</thead>

		<%-- Fetching the attributes of the request object
				which was previously set by the servlet "BookServlet.java" --%>
		<tbody>
			<c:forEach var="book" items="${booksData}">
				<tr>
					<%-- 					<td><c:out value="${bookstore.getStoreId()}" /></td> --%>
					<td><c:out value="${book.getBookName()}" /></td>
					<td><c:out value="${book.getCategory()}" /></td>
					<td><c:out value="${book.getPrice()}" /></td>
					<td><a
						href="BookServlet?action=edit&storeId=<c:out value='${bookstore.getStoreId()}'/>&bookId=<c:out value='${book.getBookId()}'/>"><button
								type="button" class="update">Update</button></a></td>
					<td><a
						href="BookServlet?action=delete&storeId=<c:out value='${bookstore.getStoreId()}'/>&bookId=<c:out value='${book.getBookId()}'/>"><button
								type="button"
								onclick="return confirm('Are you sure you want to delete this item?');"
								class="delete">Delete</button></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br><br>
	<a href="BookstoreServlet?action=list">Back to Bookstore List</a>

</body>
</html>