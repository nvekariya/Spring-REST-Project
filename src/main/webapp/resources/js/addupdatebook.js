$(document).ready(function() {
	
	// Get the book detail if bookId already exists.
	if(bookId) {
		$.ajax({
			type : 'GET',
			url : CONTEXTPATH+'/api/book/'+bookId,
			contentType : "application/json",
			dataType : "json",
			async : true,
			success : function(data, textStatus, jqXHR) {
				if(data) {
					$('#title').val(data.title);
					$('#author').val(data.author);
					$('#publisher').val(data.publisher);
					$('#edition').val(data.edition);
					$('#isbn').val(data.isbn);
					$('#quantity').val(data.quantity);
					$('#currentstock').val(data.currentstock);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR.status + ' ' + jqXHR.responseText);
			}
		});	
	}else{
		$('#currentStockField').css("display", "none");
	}
	
	// Update book details.
	var updateBook = function() {
		
		var title = $('#title').val(),
			author = $('#author').val(),
			publisher = $('#publisher').val(),
			edition = $('#edition').val(),
			isbn = $('#isbn').val(),
			quantity = $('#quantity').val(),
			currentstock = $('#currentstock').val();
		
	    var JSONObject= {
    		title:title,
    		author:author,
    		publisher:publisher,
    		edition:edition,
    		isbn:isbn,
    		quantity:quantity,
    		currentstock:currentstock
	    };
		   
		$.ajax({
			type : 'PUT',
			url : CONTEXTPATH+'/api/librarian/book/'+ bookId,
			contentType : "application/json",
			dataType : "json",
			data : JSON.stringify(JSONObject),
			async : true,
			success : function(data, textStatus, jqXHR) {
				$('#bookContainer').css("display", "none");
				$('#bookMessage').html(Messages.BookDetailUpdated).css("display", "").css("color", "black");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if(jqXHR.responseText) {
					var Obj = JSON.parse(jqXHR.responseText),
						errors = Obj.errors;					
					if(errors){
						$('#bookMessage').html(errors.join(", ")).css("display", "").css("color", "red");						
					}					
				}
				console.log(jqXHR.status + ' ' + jqXHR.responseText);
			}
		});

	};

	// Add new book.
	var addBook = function() {
		
		var title = $('#title').val(),
			author = $('#author').val(),
			publisher = $('#publisher').val(),
			edition = $('#edition').val(),
			isbn = $('#isbn').val(),
			quantity = $('#quantity').val();
		
		 var JSONObject= {
    		title:title,
    		author:author,
    		publisher:publisher,
    		edition:edition,
    		isbn:isbn,
    		quantity:quantity, 
    		currentstock:quantity // CurrentStock will be same as quantity in case of Add new book.    		
		};
		
		 $.ajax({
			type : 'POST',
			url : CONTEXTPATH+'/api/librarian/book',
			contentType : "application/json",
			dataType : 'json',
			data : JSON.stringify(JSONObject),				
			async : true,
			success : function(data, textStatus, jqXHR) {
				$('#bookContainer').css("display", "none");
				$('#bookMessage').html(Messages.BookDetailAdded).css("display", "").css("color", "black");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				if(jqXHR.responseText) {
					var Obj = JSON.parse(jqXHR.responseText),
						errors = Obj.errors;					
					if(errors){
						$('#bookMessage').html(errors.join(", ")).css("display", "").css("color", "red");						
					}					
				}
				console.log(jqXHR.status + ' ' + jqXHR.responseText);
			}
		});
	};
	
	$('#addBook').on('click', addBook);
	$('#updateBook').on('click', updateBook);			
});	