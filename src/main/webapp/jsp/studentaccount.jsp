<%@ include file="./include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Student Account</title>
		<script language="JavaScript" type="text/javascript" src="${context}/resources/js/studentaccount.js"></script>
	</head>
	<body>
		<jsp:include page="includemenu.jsp" flush="true" />
		<h2>My Account</h2><br>
		<div id="studentAccountBooks"></div>
		<div id="noBookMessage" style="display:none;">There is no book in your Loan list</div>	
	</body>
</html>