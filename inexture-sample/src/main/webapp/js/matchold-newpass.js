/**
 * 
 */
$("form").submit(function(e){
	if($("#inputoldpass").val()==$("#inputnewpass").val()){
		alert("old and new pass must be different !!");
		e.preventDefault();
	}
});