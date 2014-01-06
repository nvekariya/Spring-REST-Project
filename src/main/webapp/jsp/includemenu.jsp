<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
		<c:when test="${sessionScope.user.userRole eq 'Student'}">
			<jsp:include page="studentmenu.jsp" flush="true" />
		</c:when>
		<c:otherwise>
			<jsp:include page="librarianmenu.jsp" flush="true" />
		</c:otherwise>
</c:choose>
	
<script type="text/javascript">	
	$('#logout').on('click', function(){
		$.ajax({

			type : 'PUT',
			url : '${context}/api/logout',
			contentType : "application/json",
			dataType : "json",
			async : true,
			success : function(data, textStatus, jqXHR) {
				window.location.href = '${context}/jsp/login.jsp'; 
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status + ' ' + jqXHR.responseText);
			}
		});
	});
</script>