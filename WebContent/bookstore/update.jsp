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
	<h1>Update Bookstore Form</h1>

	<form action="BookstoreServlet?action=update" method="post">
<input type="hidden" name="storeId" value="<c:out value='${bookstoreById.getStoreId()}'/>" />
		
		<label for="storeName">Name:</label><br>
		<input type="text" name="storeName" value="<c:out value='${bookstoreById.getName()}'/>" /><br>
		<label for="storeAddress">Address:</label><br>
		<input type="text" name="storeAddress" value="<c:out value='${bookstoreById.getAddress()}'/>" /><br>
		<label for="storeTelephone">Tel:</label><br>
		<input type="text" name="storeTelephone" value="<c:out value='${bookstoreById.getTel()}'/>" /><br><br>
		
		<input type="submit" value="Update & Save">
	</form>
</body>
</html>