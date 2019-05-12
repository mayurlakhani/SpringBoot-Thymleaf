$(document).ready(function(){
/**
 * on change get game :
 * 
 */ 
	$('#gameOptId').hide();
	$('#playerlist').hide();
	$('#error').hide();
	$("#countrylist").change(function() {
		
		var countryId= $(this).val();
		
		$.ajax({
		    type: "GET",
		    url: "/getGameByCountryid", 
		    data: {
		    	countryId: countryId
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
		    	console.log(err);
		    	$('error').show();
		    	$('#error').html(err.status);
		    	alert("player not found !");
		    }
		});
	});
});  
/**
 * player list :
 * 
 */
function getPlayerList(list) {
		
		var countryId= $('#countrylist').val();
		var gameId=$(list).val();
		$.ajax({
		    type: "GET",
		    url: "/player/getplayerByGameAndCountry", 
		    data: {
		    	countryId: countryId,  
		    	gameId: gameId
		    },
		    success: function(result) {
		    	console.log(result);
		    	if(result != null)
		    		{
		    			$('#playerlist').show();
		    			
		    			$('#playerlist').html(result);
		    		}
		    },
		    error: function(err) {
		    }
		});	
}

