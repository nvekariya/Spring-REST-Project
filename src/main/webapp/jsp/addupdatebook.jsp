<%@ include file="./include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Book Detail</title>
	<script language="JavaScript" type="text/javascript" src="${context}/resources/js/addupdatebook.js"></script>
</head>
<body>
	<jsp:include page="includemenu.jsp" flush="true" />
  	<br>
	<c:choose>
		<c:when test="${empty param.bookId}">
		<h1>Add Book detail</h1>
		</c:when>
		<c:otherwise>
		<h1>Update Book detail</h1>
		</c:otherwise>
	</c:choose>
	<br>
	<div id="bookMessage" style="display:none;"></div>
	<div id="bookContainer">
		<label>Book Title:</label> <input type="text" id="title" maxlength="45"/><br>
		<label>Book Author:</label> <input type="text" id="author" maxlength="45" /><br>
		<label>Publisher:</label> <input type="text" id="publisher" maxlength="45" /><br>
		<label>Edition:</label> <input type="text" id="edition" maxlength="11" /><br>
		<label>ISBN:</label> <input type="text" id="isbn" maxlength="10" /><br>
		<label>Quantity:</label> <input type="text" id="quantity" maxlength="11" /><br>
		<div id="currentStockField"><label>Current Stock:</label> <input type="text" id="currentstock" disabled maxlength="11" /><br></div>
		<c:choose>
			<c:when test="${empty param.bookId}">
				<input type="button" id="addBook" value="Add" />
			</c:when>
			<c:otherwise>
				<input type="button" id="updateBook" value="Update" />
			</c:otherwise>
		</c:choose>
	</div>
	<script type="text/javascript">
		var bookId = '${param.bookId}';
	</script>
	</body>
</html>