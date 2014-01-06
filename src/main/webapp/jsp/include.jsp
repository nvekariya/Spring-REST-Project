<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="context" scope="request" value="<%= request.getContextPath()%>" />
<link href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript">
	
	// Set the Constant variables used across various pages.
	var CONTEXTPATH = '${context}';
	var USERROLE = '${sessionScope.user.userRole}';
	var USERNAME = '${sessionScope.user.userName}';
	
	// Dynamically load the bundle file for localization.
	var baseLang = "en";
	
	//for IE.
	if(navigator.userLanguage) baseLang = navigator.userLanguage.substring(0,2).toLowerCase();
	// Else other browser Firefox, Chrome, safari.
	else if(navigator.language) baseLang = navigator.language.substring(0,2).toLowerCase();
	
	var script = document.createElement("script");
	script.type = "text/javascript";
	script.src = "${context}/resources/bundle/messages_"+baseLang+".js";	
	document.getElementsByTagName("head")[0].appendChild(script);
	
</script>
