$(document).ready(function() {
	
	 // Get the loaned book list and display it in the table structure.	
	 $.ajax({
		type : 'GET',
		url : CONTEXTPATH+'/api/student/loanbooklist',
		contentType : "application/json",
		dataType : "json",
		async : true,
		success : function(data, textStatus, jqXHR) {
			if(data) {
				console.log(JSON.stringify(data));
				if(data.length == 0){
					$('#noBookMessage').css("display", "");
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
				    th2.appendChild(document.createTextNode("Return Book"));						        
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
					    	link.appendChild(document.createTextNode("Return"));
					    	link.setAttribute("href", "#");
					    td1.appendChild(document.createTextNode(data[i].title+", By:"+data[i].author+", Edition: "+data[i].edition));
					    td2.appendChild(link);						        
					    tr.appendChild(td1);
					    tr.appendChild(td2);
					    tbdy.appendChild(tr);
					    link.onclick = (function(bookId){
					    	return function() {
					    		$.ajax({
									type : 'PUT',
									url : CONTEXTPATH+'/api/student/returnBook/'+bookId,
									contentType : "application/json",
									dataType : "json",
									async : true,
									success : function(data, textStatus, jqXHR) {											
											$('#studentAccountBooks').css("display", "none");
											$('#noBookMessage').html(Messages.BookReturned);
											$('#noBookMessage').css("display", "");
									},
									error : function(jqXHR, textStatus, errorThrown) {
										console.log(jqXHR.status + ' ' + jqXHR.responseText);
									}
								});							    	
					    	};
					    }(data[i].bookId));
					}
					
					tbl.appendChild(thdr);
					tbl.appendChild(tbdy);
					$(tbl).appendTo('#studentAccountBooks');												
				}					
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR.status + ' ' + jqXHR.responseText);
		}
	});			
});