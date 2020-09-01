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
<!-- <link rel="stylesheet" type="text/css" href="listStyle.css"> -->
<style>
<%@ include file="/css/listStyle.css" %>
<%@ include file="/css/buttonStyle.css" %>
</style>
</head>
<body>

	<h1>Bookstores Management</h1>

<!-- 	<form> -->
<!-- 		<input class="create" type="button" -->
<!-- 			onclick="location.href='bookstore/create.jsp'" -->
<!-- 			value="Create New Bookstore!" /> -->
<!-- 	</form> -->
	<a href="bookstore/create.jsp"><button type="button" class="create">Create</button></a>
	<br><br>	
			
	<table class="bookstore_list_table">
		<thead>
			<tr>
				<th><b>Name</b></th>
				<th><b>Address</b></th>
				<th><b>Tel</b></th>
				<th><b>Update</b></th>
				<th><b>Delete</b></th>
				<th><b>Books</b></th>
			</tr>
		</thead>

		<%-- Fetching the attributes of the request object
				which was previously set by the servlet "ViewSerlet.java" --%>
		<tbody>
			<c:forEach var="bookstore" items="${bookstoresData}">
				<tr>
					<td><c:out value="${bookstore.getName()}" /></td>
					<td><c:out value="${bookstore.getAddress()}" /></td>
					<td><c:out value="${bookstore.getTel()}" /></td>
					<td><a
						href="BookstoreServlet?action=edit&storeId=<c:out value='${bookstore.getStoreId()}'/>"><button
								type="button" class="update">Update</button></a></td>
					<td><a
						href="BookstoreServlet?action=delete&storeId=<c:out value='${bookstore.getStoreId()}'/>"><button
								type="button"
								onclick="return confirm('Are you sure you want to delete this item?');"
								class="delete">Delete</button></a></td>
					<td><a
						href="BookServlet?action=list&storeId=<c:out value='${bookstore.getStoreId()}'/>"><button
								type="button" class="books">Books</button></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>