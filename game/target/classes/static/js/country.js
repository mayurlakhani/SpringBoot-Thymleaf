$(document).ready(function(){

	$("#addcountry").validate({
        
		// Specify the validation rules :
			  rules: {
			            name: "required",
			            
			        },
			        
	  // Specify the validation error messages :
			  messages: {
				        	name: "Please enter your Country",
				        	
			            },
			        
			        submitHandler: function(form) {
			            form.submit();
			        }
	});
	
});
