$(document).ready(function() {
		
	// Get the book list and display it in the table structure. 
	$.ajax({
		type : 'GET',
		url : CONTEXTPATH+'/api/booklist',
		contentType : "application/json",
		dataType : "json",
		async : true,
		success : function(data, textStatus, jqXHR) {
			if(data) {
				console.log(JSON.stringify(data));
				if(data.length == 0){
					$('#bookCatalogMessage').html(Messages.NoBookInCatalog).css("display", "").css("color", "black");	
					return;
				}
				if(data.length > 0) {		
					var tbl=document.createElement('table');
					tbl.style.width='100%';
					tbl.setAttribute('border','1');
					var thdr = document.createElement('thead');
					var trh=document.createElement('tr');
				    
				    // Create table header.
					var th1=document.createElement('th');
				    var th2=document.createElement('th');
				    th1.appendChild(document.createTextNode("Book"));
				    th2.appendChild(document.createTextNode(USERROLE == "Student" ? "Loan Book": "Edit Book Detail"));						        
				    trh.appendChild(th1);
				    trh.appendChild(th2);
				    thdr.appendChild(trh);					    
					
				    // Create table body.
				    var tbdy=document.createElement('tbody');
					for(var i = 0, len = data.length; i < len; ++i){
					    var tr=document.createElement('tr'),						    
					    	td1=document.createElement('td'),
					    	td2=document.createElement('td'),
					    	link = document.createElement("a");
					    	link.appendChild(document.createTextNode(USERROLE == "Student" ? "Loan Book": "Edit Book Detail"));
					    	link.setAttribute("href", USERROLE == "Student" ? "#" : "addupdatebook.jsp?bookId="+data[i].bookId);
					    td1.appendChild(document.createTextNode(data[i].title+", By:"+data[i].author+", Edition: "+data[i].edition));
					    td2.appendChild(link);						        
					    tr.appendChild(td1);
					    tr.appendChild(td2);
					    tbdy.appendChild(tr);
					    if(USERROLE == "Student") {
						    link.onclick = (function(bookId){
						    	return function() {
						    		$.ajax({
										type : 'PUT',
										url : CONTEXTPATH+'/api/student/loanBook/'+bookId,
										contentType : "application/json",
										dataType : "json",
										async : true,
										success : function(data, textStatus, jqXHR) {
												$('#userBookCatalog').css("display", "none");
												$('#bookCatalogMessage').html(Messages.BookLoaned).css("display", "").css("color", "black");
										},
										error : function(jqXHR, textStatus, errorThrown) {
											if(jqXHR.responseText) {
												var Obj = JSON.parse(jqXHR.responseText),
													errors = Obj.errors;					
												if(errors){
													$('#bookCatalogMessage').html(errors.join(", ")).css("display", "").css("color", "red");						
												}					
											}
											console.log(jqXHR.status + ' ' + jqXHR.responseText);
										}
									});							    	
						    	};
						    }(data[i].bookId));
					    }
					}
					
					tbl.appendChild(thdr);
					tbl.appendChild(tbdy);
					$(tbl).appendTo('#userBookCatalog');												
				}					
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});			
});	