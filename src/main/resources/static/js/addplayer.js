$(document).ready(function() {

/**
 * hide old image :
 * 
 */
	 $('input[type="file"]').change(function() {
		 $('#hideimage').hide();
     });
 /**
  * input file :
  * 
  */
	 $("#photo").fileinput({
	        maxFileCount: 1,
	        allowedFileTypes: ["image"],
	 		uploadExtraData: {kvId: '10'},
			 
	    });
/**
 * add player : validation
 * 
 */
	$("#addplayer").validate(
				{
		// Specify the validation rules :
			  rules: {
				  		gameId:  { required: true , min:0},
			            countryId: { required: true , min:0},
			            playerName:"required",
			            file: {
				                required: true, 
				                extension: "png|jpeg|jpg|gif",
				                accept: "jpg,png,jpeg,gif"
			            }
			          },
			        
	  // Specify the validation error messages :
			  messages: {
				  			gameId: "Please select Game ",
				        	countryId: "please select Country",
				        	playerName: "please add Player Name",
				        	file: "File must be JPEG or PNG, less than 1MB"
			            },
			            
			        submitHandler: function(form) {
			        	
			        	form.submit();
			           }
			           
					
	});
/**
 * update player: validation
 * 
 */		
	$("#updateplayer").validate({
	// Specify the validation rules :
		  rules: {
			  		gameId:  { required: true , min:0},
		            countryId: { required: true , min:0},
		            playerName:"required",
		            file: {
		                required: true, 
		                extension: "png|jpeg|jpg|gif",
		                accept: "jpg,png,jpeg,gif"
		            }
		          },
		        
  // Specify the validation error messages :
		  messages: {
			  			gameId: "Please select Game",
			        	countryId: "please select Country",
			        	playerName: "please add Player Name",
			        	file: "File must be JPEG or PNG, less than 1 MB"
		            },
		        
		   submitHandler: function(form) {
			   		form.submit();
		    }
});
/**
 * countryList Change event :
 * 
 */
	$("#countrylist").change(function(){
		
		var countryId= $(this).val();
		
		$.ajax({
		    type: "GET",
		    url: "/getGameByCountryid", 
		    data: {
		    	countryId: countryId,
		    	
		    },
		    success: function(result) {
		    	console.log(result);
		    	if(result != null)
		    		{
		    			$('#gameOptId').show();
		    			$('#tdId').html(result);
		    		}
		    		
		    },
		    error: function(err) {
		    }
		});
		
	});
});
/**
 * match no :
 * 
 */
   $(function(){
	   
		var $select = $(".match");
		for (i=1;i<=100;i++){
	    $select.append($('<option></option>').val(i).html(i))
		
		}
   });
/**
 * file type chechk :
 * 
 */
  /*(function($) {
	
    $.fn.checkFileType = function(options) {
        var defaults = {
            allowedExtensions: [],
            success: function() {},
            error: function() {}
        };
        options = $.extend(defaults, options);

        return this.each(function() {

            $(this).on('change', function() {
	                var value = $(this).val(),
	                    file = value.toLowerCase(),
	                    extension = file.substring(file.lastIndexOf('.') + 1);
	
	                if ($.inArray(extension, options.allowedExtensions) == -1) {
	                    options.error();
	                    $(this).focus();
	                } else {
	                    options.success();
	
	                }
            });
        });
    };
})(jQuery);

$(function() {
    $('#photo').checkFileType({
        allowedExtensions: ['jpg', 'jpeg','png','gif'],
        success: function() {
            alert('Success');	
        },
        error: function(result) {
        	
            alert('please selecte jpeg or jpg image only');
        }
    });

});*/

