 
/**
 * player list :
 * 
 */
function delplayer(id) {
	
		$.ajax({
		    type: "GET",
		    url: 'delete/'+ id, 
		    success: function(result) {
		    	console.log(result);
		    	if(result != null)
	    		{
	    			
	    			$('#').html(result);
	    		}
	    		
		    },
		    error: function(err) {
		    }
		});	
}

