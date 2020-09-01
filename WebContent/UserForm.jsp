<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="bookstore.javabeans.Bookstore"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Form</title>
</head>
<body>

	<h1>Update Bookstore Form</h1>

	<c:if test="${bookstore == null}">
<!-- 		<form action="insert" method="post"> -->
	</c:if>
	
	<c:if test="${bookstore != null}">
<!-- 		<form action="update" method="post"> -->
	</c:if>

	<table>
		<c:if test="${bookstore != null}">
			<input type="hidden" name="storeId"
				value="<c:out value='${bookstoreById.getStoreId()}'/>" />
		</c:if>

		<tr>
			<th>Name:</th>
			<td><input type="text" name="storeName"
				value="<c:out value='${bookstoreById.getName()}'/>" /></td>
		</tr>
		<tr>
			<th>Address:</th>
			<td><input type="text" name="storeAddress"
				value="<c:out value='${bookstoreById.getAddress()}'/>" /></td>
		</tr>
		<tr>
			<th>Tel:</th>
			<td><input type="text" name="storeTelephone"
				value="<c:out value='${bookstoreById.getTel()}'/>" /></td>
		</tr>
		<tr>
<!-- 			<input type="submit" value="Save Bookstore" /> -->
		</tr>
	</table>
<!-- 	</form> -->


	<%-- 	<% --%>
	<!-- // 	Bookstore bookstoreById = (Bookstore)request.getAttribute("bookstoreById"); -->
	<%-- 	%> --%>

	<!-- 	<form name="updateBookstoreForm" method="post" action="UpdateServlet2"> -->
	<%-- 		<input type="hidden" name="storeId" value="<%=bookstoreById.getStoreId()%>"> --%>

	<!-- 		<label for="storeName">Name:</label><br> -->
	<%-- 		<input type="text" name="storeName" value="<%=bookstoreById.getName()%>"><br> --%>
	<!-- 		<label for="storeAddress">Address:</label><br> -->
	<%-- 		<input type="text" name="storeAddress" value="<%=bookstoreById.getAddress()%>"><br> --%>
	<!-- 		<label for="storeTelephone">Tel:</label><br> -->
	<%-- 		<input type="text" name="storeTelephone" value="<%=bookstoreById.getTel()%>"><br><br> --%>

	<!-- 		<input type="submit" value="Update & Save"> -->
	<!-- 	</form> -->

</body>
</html>