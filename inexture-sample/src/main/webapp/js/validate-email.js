/**
 * 
 */

var flag;
function getFlag(){
	return flag;
}
function checkUser(getCheckUserFlag){
			$.ajax({
			//	async: false,
				url : 'CheckUser',
				
				data : {
					email : $('#inputemailid').val()
				},
				success :  function getCheckUserFlag(responseText) {
				//	$('#ajaxGetUserServletResponse').text(responseText);
					flag = true;
				var obj = responseText;
		     	
		     	if(obj.bool === '1') {
		     	$("#demo").html("");
		     	
		     	$("#mybutton").attr("disabled",false);
		     	 $("#inputemailid").parents(".form-group.has-feedback").addClass("has-success");
		    	 
		     	}else{
		     		 flag = false;
		    	 $("#demo").html(obj.result);
		    	 $("#inputemailid").parents(".form-group.has-feedback.has-success").removeClass("has-success");
		    	 $("#inputemailid").parents(".form-group.has-feedback").addClass("has-error has-danger");
		    	 $("#mybutton").attr("disabled",true);
		    	
		     	}
				}
			});
	}
