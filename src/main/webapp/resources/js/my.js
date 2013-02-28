
/*------------------------------------------------------------------- Page 3 Ausgaben -----------------------------------------------------------------------------------------------*/


function form_ausgabenform_select_account_options_create(){	
	 $.ajax({
		 url: "rest/costs/accounts",
		 cache: false,
		 dataType: "xml",
		 success: function(xmldata) {
			 $(xmldata).find("account").each(function() {
			 var id = $(this).find("id").text();
			 var label = $(this).find("label").text();
			 	$('#accounts').append( '<option value="'+id+'">'+label+'</option>');
			 });
	     },
	     	error: function(error) {
	        console.log("error updating table -" + error.status);
	     }
	});

}

function form_ausgabenform_select_subAccount_options_create(){
	var account_id = $("#accounts").val();
	if(account_id=="default"){
		$('#subaccounts')
        .find('option')
        .remove()
        .end();	
	}else{
		$.ajax({
		url: "rest/costs/accounts/subaccounts/"+account_id,
		dataType: "xml",
		cache: false,
		success: function(xmldata) {
			$('#subaccounts')
	        .find('option')
	        .remove()
	        .end();
			$(xmldata).find("subAccount").each(function() {
				var id = $(this).find("id").text();
			    var label = $(this).find("label").text();
			    //alert("id: "+id+" Label: "+label );
			    $('#subaccounts')
			    .find('option')
			    .end()
			    .append('<option value="'+id+'">'+label+'</option>');
			});
			   
	     },
	     error: function(error) {
	    	 console.log("error updating table -" + error.status);
	     }
	     });
	}
}

function form_ausgabenform_input_btn_buchen_exceute(formValues) {

	   //clear existing  msgs
	   $('span.invalid').remove();
	   $('span.success').remove();

	   $.post('rest/costs', formValues,
	         function(data) {
	            console.log("Expenditures registered");

	            //clear input fields
	            $('#form-ausgaben')[0].reset();

	            //mark success on the registration form
	            $('#formausgabenMsgs').append($('<span class="success">Kosten wurden verbucht</span>'));

	            
	         }).error(function(error) {
	            if ((error.status == 409) || (error.status == 400)) {
	               console.log("Validation error registering user!");

	               var errorMsg = JSON.parse(error.responseText);

	               $.each(errorMsg, function(index, val){
	                  $('<span class="invalid">' + val + '</span>')
	                        .insertAfter($('#' + index));
	               });
	            } else {
	               console.log("error - unknown server issue");
	               $('#formausgabenMsgs').append($('<span class="invalid">Der Server antwortet nicht. wiederholen sie den Vorgang in 10 Minuten.</span>'));
	            }
	         });
}

/*------------------------------------------------------------------- Ende Page 3 Ausgaben -----------------------------------------------------------------------------------------------*/


