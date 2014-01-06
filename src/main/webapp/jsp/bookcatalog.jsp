<%@ include file="./include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Book Catalog</title>
		<script language="JavaScript" type="text/javascript" src="${context}/resources/js/bookcatalog.js"></script>
	</head>
	<body>
		<jsp:include page="includemenu.jsp" flush="true" />
		<br><h1>Book Catalog</h1>
		<div id="bookCatalogMessage" style="display:none;"></div>	
		<div id="userBookCatalog"></div>		
	</body>
</html>