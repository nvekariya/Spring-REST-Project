<%@ include file="./include.jsp"%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>NPU Library Login</title>
		<script language="JavaScript" type="text/javascript" src="${context}/resources/js/login.js"></script>
	</head>
	<body>
		<div class="mainContainer">
			<img src="${context}/resources/images/npu_logo.jpeg" />
			<h1>Welcome to NPU Library System</h1>
			<div id="errorMessage" style="display: none; color: red"></div>
			<label>UserName: </label>
			<input type="text" id="username" value="" placeholder="Username"></input>
			<br>
			<label>Password: </label>
			<input type="password" id="password" value="" placeholder="Password"></input>
			<br>
			<input type="button" id="login" value="Login"/>
		</div>	
	</body>
</html>