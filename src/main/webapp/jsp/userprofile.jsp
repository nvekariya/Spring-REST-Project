<%@ include file="./include.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>My Profile</title>
		<script language="JavaScript" type="text/javascript" src="${context}/resources/js/userprofile.js"></script>
	</head>
	<body>
	
		<jsp:include page="includemenu.jsp" flush="true" />
		<br><h2>My Profile</h2>
		<div id="profileMessage" style="display: none;">Updated Successfully!!!</div>
	  	<br>
		<label>User Name:</label>	<input type="text" id="userName" maxlength="45" /><br>
		<!-- Don't display password value, instead display default -->
		<label>Password:</label>	<input type="password" id="password" value="**************" maxlength="45" /><br>
		<label>First Name:</label>	<input type="text" id="firstName" maxlength="45" /><br>
		<label>Last Name:</label>	<input type="text" id="lastName" maxlength="45" /><br>
		<label>Street:</label> 		<input type="text" id="street" maxlength="100" /><br>
		<label>City:</label> 		<input type="text" id="city" maxlength="45" /><br>
		<label>State:</label> 		<input type="text" id="state" maxlength="45" /><br>
		<label>ZipCode:</label> 	<input type="text" id="zipcode" maxlength="11" /><br>
		<label>Email ID:</label>	<input type="text" id="email" maxlength="45" /><br>
	
		<input type="button" id="updateProfile" value="Update" />
		
	</body>
</html>