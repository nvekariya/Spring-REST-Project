$(document).ready(function() {
	var userId="", userRole="";
	
	// Get the user profile.
	$.ajax({
		type : 'GET',
		url : CONTEXTPATH+'/api/profile/'+USERNAME,
		contentType : "application/json",
		dataType : "json",
		async : true,
		success : function(data, textStatus, jqXHR) {
			if(data) {
				$('#userName').val(data.userName);
				$('#firstName').val(data.firstName);
				$('#lastName').val(data.lastName);
				$('#street').val(data.street);
			 	$('#city').val(data.city);
			 	$('#state').val(data.state);
			 	$('#zipcode').val(data.zipcode);
				$('#email').val(data.email);
				userId = data.userId;
				userRole = data.userRole;
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});
	
	// Update user profile.
	var updateUserProfile = function() {
		
		$('#profileMessage').css("display", "none");
		
		var userName = $('#userName').val(),
			password = $('#password').val(),
			firstName = $('#firstName').val(),
			lastName = $('#lastName').val(),
			street = $('#street').val(),
		 	city = $('#city').val(),
		 	state = $('#state').val(),
		 	zipcode = $('#zipcode').val(),
			email = $('#email').val();	
		
		var userProfileObj = {
				userId : userId,
				userRole : userRole,
				userName : userName,
				password : (password == '**************')?null:password,
				firstName : firstName,
				lastName : lastName,
				street : street,
			 	city : city,
			 	state : state,
			 	zipcode : zipcode,
				email : email					
		};
	
		$.ajax({
	
			type : 'PUT',
			url : CONTEXTPATH+'/api/profile',
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(userProfileObj),
			async : true,
			success : function(result) {
				$('#profileMessage').html(Messages.UserProfileUpdated).css("display", "").css("color", "black");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if(jqXHR.responseText) {
					var Obj = JSON.parse(jqXHR.responseText),
						errors = Obj.errors;					
					if(errors){
						$('#profileMessage').html(errors.join(", ")).css("display", "").css("color", "red");						
					}					
				}
				console.log(jqXHR.status + ' ' + jqXHR.responseText);
			}
		});
	};
	
	$('#updateProfile').on('click', updateUserProfile);
});