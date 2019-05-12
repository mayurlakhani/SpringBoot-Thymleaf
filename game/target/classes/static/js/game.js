$(document).ready(function(){
$("#addgame").validate({
			        
		// Specify the validation rules :
			  rules: {
			            gameName: "required",
			            
			        },
			        
	  // Specify the validation error messages :
			  messages: {
				        	gameName: "Please enter your Game name",
				        	
			            },
			        
			        submitHandler: function(form) {
			            form.submit();
			        }
		});
});