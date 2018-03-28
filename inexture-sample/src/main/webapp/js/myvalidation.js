/**
 * 
 */
var docheck = true;
	function doCheck(bool){
		docheck = bool;
		if(docheck == false){
			$("#confpassdiv").hide();
			$("#emaildiv").hide();
			$("#pwddiv").hide();
			}
		}

	


	function checkImage(){
		var fileupload = document.getElementById("picupload");
		var file,flag = true;
		for(var i=0;i<fileupload.files.length;i++){
			file = fileupload.files[i];
			if ('name' in file) {
               console.log(file.name);
               var filename = file.name;
               var ext = filename.split('.').pop().toLowerCase();
               console.log('extension' + ext);
               if($.inArray(ext, ['jpg','jpeg']) == -1) {
            	   	$("#picupload").parents(".form-group.has-feedback").addClass("has-error has-danger");
            	   	$("#mybutton").attr("disabled",true);
			    	$("#fluploadmsg").html("invalid extensions try uploading only jpg and jpeg files");
            	  	flag = false;
            	  	break;
            	}
				
            }
		}
		if(flag==true){
	    	$("#fluploadmsg").html("");
	    	$("#picupload").parents(".form-group.has-feedback").removeClass("has-error has-danger");
	    	$("#picupload").parents(".form-group.has-feedback").addClass("has-success");
	    	$("#mybutton").attr("disabled",false);
		}
		return flag;
	}
	
	function determineCheck(){
		var checkboxes = document.getElementsByName("lang");
		var flag = false;
		for(var i=0;i<checkboxes.length;i++){
			if(checkboxes[i].checked == true){
				flag=true;
				break;
			}
		}
		return flag;
	}
	function validateCheck(){

		
			if(determineCheck()){
				$("#mybutton").attr("disabled",false);
				$(".checkbox").parent().parent().removeClass("has-error has-danger");
				$(".checkbox").parent().parent().addClass("has-success");
				//document.getElementById("myform").submit();
				return true
			}else{
				$(".checkbox").parent().parent().removeClass("has-success");
				$(".checkbox").parent().parent().addClass("has-error has-danger");
				$(".checkbox").parent().parent().addClass("has-error has-danger");
				$("#mybutton").attr("disabled",true);
				return false
			}
		
	}
	
	$(document).ready(function(){
	$("form").submit(function(e){
     	 alert('submit intercepted');
     	 $("#myform").validator('update');
//if(docheck == true){
//     	if(!checkUser())
//   			 e.preventDefault(e);
//}
        		if(!validateCheck())
     			 e.preventDefault(e);
            		if(!checkImage())
        			 e.preventDefault(e);
     });

     	$('.datepicker').datepicker({
     		format: 'yyyy-mm-dd',
     		endDate: '+0d'
     	});
	$("#myform").on("input",".only-number",function(){
		var input = $(this).val();
		if(!/^[0-9]+$/.test(input)){
		var newinput	= input.substr(0,input.length-1);
		$(this).val(newinput);
		}else{
			$(this).val(input);
		}
	});
	});
