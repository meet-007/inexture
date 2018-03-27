/**
 * 
 */
function checkUser(){
		var flag = true;
			$.ajax({
				url : 'CheckUser',
				data : {
					email : $('#inputemailid').val()
				},
				success : function(responseText) {
				//	$('#ajaxGetUserServletResponse').text(responseText);
				var obj = responseText;
		     	
		     	if(obj.bool === '1') {
		     	$("#demo").html("");
		     	
		     	$("#mybutton").attr("disabled",false);
		     	 $("#inputemailid").parents(".form-group.has-feedback").addClass("has-success");
		    	 flag = true;
		     	}else{
		    	 $("#demo").html(obj.result);
		    	 $("#inputemailid").parents(".form-group.has-feedback.has-success").removeClass("has-success");
		    	 $("#inputemailid").parents(".form-group.has-feedback").addClass("has-error has-danger");
		    	 $("#mybutton").attr("disabled",true);
		    	 flag = false;
		     	}
				}
			});
	
	return flag;
	}