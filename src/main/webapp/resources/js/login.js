$(document).ready(function() {
	
	// Validate user credentials, if success navigate users to the home page.	
	var validateUser = function() {
		var username = $('#username').val();
		var password = $('#password').val();
		
		if(!username.trim() || !password.trim()) {
			$('#errorMessage').html(Messages.InvalidCredentials).css("display", "").css("color", "red");
		} else {
			
			$.ajax({

				type : 'POST',
				url : CONTEXTPATH+'/api/login',
				contentType : "application/json",
				dataType : "json",
				data : JSON.stringify({userName: username, password: password}),
				async : true,
				success : function(result) {
					window.location.href = CONTEXTPATH+'/jsp/user.jsp'; 
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if(jqXHR.responseText) {
						var Obj = JSON.parse(jqXHR.responseText),
							errors = Obj.errors;					
						if(errors){
							$('#errorMessage').html(errors.join(", ")).css("display", "").css("color", "red");						
						}					
					}
					console.log(jqXHR.status + ' ' + jqXHR.responseText);
				}
			});
		}
	};
	
	$('#login').on('click', validateUser);
});